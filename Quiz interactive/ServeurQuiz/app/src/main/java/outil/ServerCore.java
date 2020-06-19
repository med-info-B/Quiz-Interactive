package outil;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.mbms.MbmsErrors;
import android.widget.Button;

import java.io.IOException;

import Controller.HandleClient;
import vue.MainActivity;

/**
 *
 */
public class ServerCore extends  Thread {


    private BluetoothServerSocket serverSocket ;
    private BluetoothAdapter bluetoothAdapter;
    private Handler handler;
    private Context context;
    /**
     *
     * @param context
     * @param handler
     * @param adapter
     */
    public ServerCore(Context context, Handler handler, BluetoothAdapter adapter){
        this.handler = handler;
        this.context = context;
        this.bluetoothAdapter = adapter;
        BluetoothServerSocket tmp = null;
        try {
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord( MainActivity.My_APP_Name, MainActivity.MY_UUID );
        } catch (IOException e) {
            MainActivity.textView.setText( "Erreur " );
        }
        serverSocket = tmp;
    }
    /**
     *
     */
    @Override
    public void run() {
        super.run();
        while (true) {
            try{
                BluetoothSocket s = serverSocket.accept();
                new Thread( new HandleClient(s) ).start();
                Message message = Message.obtain();
                message.what = MainActivity.CONNECTED;

                handler.sendMessage( message );
            } catch (IOException e) {
                Message message = Message.obtain();
                message.what = MainActivity.NCONNECTED;
                handler.sendMessage( message );
                break;
            }

        }

    }


}



