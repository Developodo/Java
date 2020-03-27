package sockets;
//https://examples.javacodegeeks.com/core-java/nio/java-nio-socket-example/
import java.io.IOException;


public class Test {
    public static void main(String[] args) {
        try {
            Runnable server = new Runnable() {
                @Override
                public void run() {
                    try {
                        new Server().startServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }
            };
            
            Runnable client = new Runnable() {
                @Override
                public void run() {
                    try {
                        new Client().startClient();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                }
            };
            new Thread(server).start();
            Thread.sleep(2000);
            new Thread(client, "client-A").start();
            new Thread(client, "client-B").start();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
