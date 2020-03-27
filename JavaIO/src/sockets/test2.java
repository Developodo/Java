package sockets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test2 {
    public static void main(String[] args) {
        try {
            new Client().startClient();
        } catch (IOException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
