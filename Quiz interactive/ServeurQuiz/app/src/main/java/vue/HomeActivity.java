package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.serveurquiz.R;

/**
 *      Home activity class
 */
public class HomeActivity extends AppCompatActivity {

    private Button button;
    private MediaPlayer player;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        button = findViewById( R.id.button );
    }

    /**
     *   listening of button which is identified by id button
     * @param v
     */
    public void start(View v ){
        if(player != null){
            player.start();
        }
        intent = new Intent( HomeActivity.this, MainActivity.class );
        startActivity( intent );
    }


    /**
     *  this method represents a state in the life cycle of this activity
     *  which is implemented when the activity is launched
     */
    @Override
    protected void onResume() {
        super.onResume();
        player = MediaPlayer.create( this, R.raw.clic );
    }

}
