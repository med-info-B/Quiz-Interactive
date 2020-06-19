package outil.protocol;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *  class assume deux responsabilitées, L'Analyse la commande et la notification du contrôlleur
 */
public class QuizInput {

    private QuizProtocol handle;
    private InputStream in;
    private boolean stop = false;

    public QuizInput(InputStream in, QuizProtocol handle){
        this.in =in;
        this.handle = handle;
    }

    /**
     *  Partie représente l'analyse de la commande et la notification du contrôlleur
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doRun(){
        String msg;
        try(BufferedReader is = new BufferedReader( new InputStreamReader( in ) )){
            while (!stop){
                String line = is.readLine();
                switch (line){
                    case "NAME" :    msg = is.readLine();
                                     handle.sendName( msg );
                                     break;
                    case "NAME OK" : handle.sendNameOk();
                                     break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
