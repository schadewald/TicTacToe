import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * Chad Schadewald
 * Game.java
 * Project 3
 */
public class Game implements Runnable
{
    private int currentPlayer = 8;
    private Socket s;
    private Scanner in;
    private PrintWriter out;

    public Game(Socket socket)
    {
        s = socket;
    }
    @Override
    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void doService() throws IOException
    {
        while (true)
        {
            if(!in.hasNext()) return;
            String command = in.next();
            if (command.equals("QUIT")) return;
            else executeCommand(command);
        }
    }
    public void executeCommand(String command)
    {
        System.out.println("Command at start of executeCommand is: " + command);
        if (command.equals("START"))
        {
            System.out.println("Start command received.");
            if (currentPlayer == 8)
            {
                currentPlayer = 1;
            }
            else
            {
                currentPlayer = 2;
            }
            out.println("Hello you are player " + currentPlayer);
            out.flush();
        }
        else if (!command.equals("PLAYERMOVE"))
        {
            out.println("Invalid command.");
            out.flush();
            return;
        }
        System.out.println("Command complete.");
        out.println("Returning Message: Command Complete.");
        out.flush();
    }
}