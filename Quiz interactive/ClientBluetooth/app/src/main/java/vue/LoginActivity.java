package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clientbluetooth.R;


/**
 *
 class takes responsibility for authenticating users and directing it to suitable interfaces
 */
public class LoginActivity extends AppCompatActivity {

    private Intent intent_sign, intent_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
    }

    public void login(View v){
        intent_login = new Intent( LoginActivity.this, MenuActivity.class );
        startActivity( intent_login );
    }

    public void signup(View v){
        intent_sign = new Intent( LoginActivity.this, LoginFormActivity.class );
        startActivity( intent_sign );
    }
}
