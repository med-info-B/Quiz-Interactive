package outils.protocol;

import java.io.OutputStream;
import java.io.PrintWriter;

public class QuizOutput implements QuizProtocol {

    private PrintWriter os;

    public QuizOutput(OutputStream out){
        os = new PrintWriter( out, true );
    }

    @Override
    public void sendName(String name) {
        os.println("NAME");
        os.println(name);
    }


}
