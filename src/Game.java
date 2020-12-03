import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Game implements Runnable
{
    private int currentPlayer = 0;
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
        if (command.equals("TEST"))
        {
            System.out.println("Test received.");
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