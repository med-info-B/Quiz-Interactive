package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clientbluetooth.R;

import java.util.Set;

import model.Client;
import controller.MyHandler;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter ;
    public TextView textView;
    Button button;
    LinearLayout linearLayout;
    MyHandler  handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        initialisation();


        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                BluetoothDevice d = null;
                for(BluetoothDevice device : devices){
                    d =device;
                    break;
                }
                Client clientQuiz = new Client( d , bluetoothAdapter, handler);
                clientQuiz.connect();
            }
        } );
    }

    private void initialisation(){
        button = findViewById( R.id.button);
        textView = findViewById( R.id.textView );
        linearLayout = findViewById( R.id.linerLayout );
        handler = new MyHandler(linearLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }
}






