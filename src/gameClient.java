import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * Chad Schadewald
 * gameClient.java
 * Project 4
 */
public class gameClient extends Application
{
    GridPane gridPaneOne = new GridPane();
    Cell[][] cellOne = new Cell[3][3];
    public int currentPlayer = 0;
    private int playerNumber = 0;

    public static void main(String[] args) throws IOException
    {
        final int PORT = 8888;
        Socket s = new Socket("localhost", PORT);
        InputStream inStream = s.getInputStream();
        OutputStream outStream = s.getOutputStream();
        Scanner in = new Scanner(inStream);
        PrintWriter out = new PrintWriter(outStream);
        System.out.println("Testing gameClient.");
        String command = "START\n";
        System.out.println("Sending: " + command);
        out.print(command);
        out.flush();
        String response = in.nextLine();
        System.out.println("Receiving: " + response);
        s.close();

        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        for (int i = 0; i < 3; i ++)
        {
            for (int j = 0; j < 3; j++)
            {
                cellOne[i][j] = new Cell();
                gridPaneOne.add(cellOne[i][j], j, i);
            }
        }
        primaryStage.setTitle("Tic Tac Toe!");
        Scene playerOneScene = new Scene(gridPaneOne, 450, 300);
        primaryStage.setScene(playerOneScene);
        primaryStage.setX(1000);
        primaryStage.setY(100);
        primaryStage.show();

    }
    public boolean isBoardFull()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (cellOne[i][j].getPlayer() == 8)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean hasWon(int player)
    {
        for (int i = 0; i < 3; i++)
        {
            if (cellOne[i][0].getPlayer() == player && cellOne[i][1].getPlayer() == player && cellOne[i][2].getPlayer() == player)
            {
                return true;
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if (cellOne[0][i].getPlayer() == player && cellOne[1][i].getPlayer() == player && cellOne[2][i].getPlayer() == player)
            {
                return true;
            }
        }
        if (cellOne[0][0].getPlayer() == player && cellOne[1][1].getPlayer() == player && cellOne[2][2].getPlayer() == player)
        {
            return true;
        }
        if (cellOne[0][2].getPlayer() == player && cellOne[1][1].getPlayer() == player && cellOne[2][0].getPlayer() == player)
        {
            return true;
        }
        return false;
    }
    public class Cell extends Pane
    {
        private int player = 8;
        public Cell()
        {
            setStyle("-fx-border-color: black");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(e -> handleClick());
        }
        private void handleClick()
        {
            if (player == 8)
            {
                if (currentPlayer % 2 == 0)
                {
                    setPlayer(0);
//                    setCell(0);
                    currentPlayer++;
                }
                else
                {
                    setPlayer(1);
//                    setCell(1);
                    currentPlayer++;
                }
                if (hasWon(this.player))
                {
                    String playerColor = "";
                    if (player == 0)
                    {
                        playerColor = "Blue";
                    }
                    if (player == 1)
                    {
                        playerColor = "Red";
                    }
                    System.out.println(playerColor + " has won!");
                }
                if (isBoardFull()) {
                    System.out.println("Draw!");
                }
            }
        }
        public int getPlayer()
        {
            return this.player;
        }
        public void setPlayer(int p)
        {
            this.player = p;
            if (player == 0)
            {
                setStyle("-fx-background-color: blue; -fx-border-color: black");
            }
            else if (player == 1)
            {
                setStyle("-fx-background-color: red; -fx-border-color: black");
            }
        }
//        public void setCell(int p)
//        {
//            int colRand = (int)(Math.random() * (2 - 0 + 1) + 0);
//            int rowRand = (int)(Math.random() * (2 - 0 + 1) + 0);
//            if (cellTwo[rowRand][colRand].getPlayer() == 8)
//            {
//                cellTwo[rowRand][colRand].setPlayer(p);
//                System.out.println("Setting Row: " + rowRand + " and Col: " + colRand);
//            }
//            else
//            {
//                System.out.println("Row: " + rowRand + " and Col: " + colRand + " are taken, re-calling setCell.");
//                setCell(p);
//            }
//        }
    }
}