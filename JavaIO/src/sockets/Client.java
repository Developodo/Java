package sockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    String address;
    int port;
    
    public Client(){
        address="localhost";
        port=8090;
    }
    
    public void startClient()
            throws IOException, InterruptedException {
        System.out.println("Intentando conectar a "+address+":"+port);
        InetSocketAddress hostAddress = new InetSocketAddress(address, port);
        SocketChannel client = SocketChannel.open(hostAddress);
  
        System.out.println("Client... started");
         
        String threadName = Thread.currentThread().getName();
  
        // Send messages to server
        String [] messages = new String [] 
                {threadName + ": test1",threadName + ": test2",threadName + ": test3"};
  
        for (int i = 0; i < messages.length; i++) {
            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            //System.out.println(messages [i]);
            buffer.clear();
            Thread.sleep(Math.round(Math.random()*10000));
        }
        client.close();            
    }
}
