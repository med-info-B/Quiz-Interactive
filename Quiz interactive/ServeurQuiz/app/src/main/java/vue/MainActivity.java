package vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.serveurquiz.R;

import java.util.UUID;
import outil.ServerCore;


public class MainActivity extends AppCompatActivity {


    public static  final String My_APP_Name = "ServeurBluetooth";
    public static  final UUID MY_UUID = UUID.fromString("f94c3a96-c10b-4a0c-906f-d3e3c32024ef");
    public static final int CONNECTED = 1;
    public static final int NCONNECTED = 2;
    public static final  int TEST = 4;
    public static final int STARt = 3;
    public static  final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public static TextView textView ;
    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button button = findViewById( R.id.button);
        textView = findViewById( R.id.textView );
        handler  = new Handler( new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case CONNECTED : textView.setText( " Client est connecté" );
                        break;
                    case NCONNECTED : textView.setText( "non connecté " );
                        break;
                    case STARt :        textView.setText( "connected server" );
                        break;
                    case TEST :        textView.setText( "TEST : envoie name OK" );
                        break;
                    default:           textView.setText( "  aucun indication " );
                        break;
                }
                return false;
            }
        } );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ServerCore(getApplicationContext(), handler, bluetoothAdapter).start();
                Message message = Message.obtain();
                message.what = STARt;
                handler.sendMessage(message);

            }
        } );

    }


}






