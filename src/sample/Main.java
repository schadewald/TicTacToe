package sample;

import java.io.IOException;
import java.net.ServerSocket;

public class Main
{
    public static void main(String[] args) throws IOException {
        final int PORT = 8888;
        ServerSocket server = new ServerSocket(PORT);

        gameClient testClient = new gameClient();
        gameClient testClient2 = new gameClient();
        Thread thread = new Thread(testClient);
        Thread thread1 = new Thread(testClient2);
        thread.start();
        thread1.start();
    }
}
