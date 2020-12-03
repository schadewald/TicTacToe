import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Chad Schadewald
 * Main.java
 * Project 4
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        final int PORT = 8888;
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Waiting for client to connect...");
        while (true)
        {
            Socket s = server.accept();
            System.out.println("Client connected.");
            Game game = new Game(s);
            Thread t = new Thread(game);
            t.start();
        }

    }
}