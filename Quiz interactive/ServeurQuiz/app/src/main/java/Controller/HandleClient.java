package Controller;

import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Message;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import outil.protocol.QuizInput;
import outil.protocol.QuizOutPut;
import outil.protocol.QuizProtocol;
import vue.MainActivity;

/**
 *  Class représente le Controlleur qui assumer le role intermédiare entre le model et la vue ( E / S)
 */
public class HandleClient implements  Runnable, QuizProtocol{
    BluetoothSocket socket;
    QuizInput input;
    QuizOutPut outPut;

    public HandleClient(BluetoothSocket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void sendName(String name) {
      sendNameOk();
    }

    @Override
    public void sendNameOk() {
        Message ms = Message.obtain();
        ms.what = MainActivity.TEST;
        MainActivity.handler.sendMessage( ms );
        outPut.sendNameOk();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        try(BluetoothSocket s = socket) {
            outPut = new QuizOutPut(s.getOutputStream());
            input = new QuizInput( s.getInputStream(), this );
            input.doRun();
        }catch (Exception e){

        }
    }
}
