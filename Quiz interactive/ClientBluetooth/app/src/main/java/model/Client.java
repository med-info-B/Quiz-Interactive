package model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;

import controller.HandleConnction;

import outils.Constantes;
import controller.MyHandler;

import static android.content.ContentValues.TAG;

public class Client {
    private BluetoothSocket socket;
    private BluetoothDevice device;
    private BluetoothAdapter adapter;
    private HandleConnction handleConnction;
    private MyHandler handler;
    public Client(BluetoothDevice device, BluetoothAdapter adapter, MyHandler handler) {
        BluetoothSocket tmp = null;
        this.device = device;
        this.handler = handler;
        this.adapter = adapter;
        try {
            tmp = device.createRfcommSocketToServiceRecord( Constantes.MY_UUID );
        } catch (IOException e) {
            Log.e( TAG, "non", e );
        }
        socket = tmp;
    }

    public void connect() {
        adapter.cancelDiscovery();
        try {
            socket.connect();
            handleConnction = new HandleConnction( socket, handler );
            handleConnction.start();
            handleConnction.sendName("med");


        } catch (IOException e) {
            Log.e( TAG, "non coonect", e );
        }
    }
}
