package controller;

import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import model.Client;
import outils.Constantes;
import outils.protocol.QuizInput;
import outils.protocol.QuizOutput;
import outils.protocol.QuizProtocol;

/**
 *    Cette Class représente le contrôlleur assumant la responsabilité de notifier l'observer (client) et d'envoyer l'info au serveur
 */
public class HandleConnction extends Thread implements QuizProtocol {
    private BluetoothSocket socket;
    private QuizInput input;
    private QuizOutput output;
    private Client clientQuiz;
    private MyHandler handler;
    private Bundle bundle;
    private Message msg;

    public HandleConnction(BluetoothSocket socket, MyHandler handler) throws IOException {
        this.socket = socket;
        this.clientQuiz = clientQuiz;
        this.handler = handler;
        bundle = new Bundle();
        output = new QuizOutput( socket.getOutputStream() );
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        try (BluetoothSocket sock = socket) {
            input = new QuizInput( sock.getInputStream(), this );
            input.doRun();
        } catch (Exception e) {

        }
    }


    @Override
    public void sendName(String name) {
        output.sendName(name);
    }

    @Override
    public void sendNameOK() {
        msg=  handler.obtainMessage();
        bundle.putString( "Name ok"," good good" );
        msg.setData( bundle );
        handler.sendMessage( msg );
    }


}
