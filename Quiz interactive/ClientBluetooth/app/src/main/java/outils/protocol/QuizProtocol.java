package outils.protocol;


public interface QuizProtocol {
        default  void sendName(String name){};
        default void sendNameOK(){};
}
