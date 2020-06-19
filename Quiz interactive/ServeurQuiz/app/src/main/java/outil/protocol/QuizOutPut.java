package outil.protocol;

import java.io.OutputStream;
import java.io.PrintWriter;

public class QuizOutPut implements QuizProtocol {
    PrintWriter os;

    public QuizOutPut(OutputStream stream){
        os = new PrintWriter( stream,true );
    }
    @Override
    public synchronized void sendName(String name) {
        os.println("NAME");
        os.println( name );
    }

    @Override
    public synchronized  void sendNameOk() {
        os.println("NAME OK");
    }
}
