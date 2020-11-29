package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.net.ServerSocket;

public class Main extends Application
{
    GridPane gridPaneOne = new GridPane();
    GridPane gridPaneTwo = new GridPane();
    Cell[][] cell = new Cell[3][3];
    private int currentPlayer = 0;
    @Override
    public void start(Stage playerOneStage) throws Exception
    {
        for (int i = 0; i < 3; i ++)
        {
            for (int j = 0; j < 3; j++)
            {
                cell[i][j] = new Cell();
                gridPaneOne.add(cell[i][j], j, i);
                cell[i][j] = new Cell();
                gridPaneTwo.add(cell[i][j], j, i);
            }
        }

        gameClient testClient = new gameClient();
        gameClient testClient2 = new gameClient();
        Thread thread = new Thread(testClient);
        Thread thread1 = new Thread(testClient2);
        thread.start();
        thread1.start();

        Stage playerTwoStage = new Stage();
        playerOneStage.setTitle("Tic Tac Toe Player 1");
        playerTwoStage.setTitle("Tic Tac Toe Player 2");
        Scene playerOneScene = new Scene(gridPaneOne, 450, 300);
        Scene playerTwoScene = new Scene(gridPaneTwo, 450, 300);
        playerOneStage.setScene(playerOneScene);
        playerTwoStage.setScene(playerTwoScene);
        playerOneStage.setX(1000);
        playerOneStage.setY(100);
        playerTwoStage.setX(1000);
        playerTwoStage.setY(500);
        playerOneStage.show();
        playerTwoStage.show();
    }
    public class Cell extends Pane
    {
        private int player = 0;
        public Cell()
        {
            setStyle("-fx-border-color: black");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(e -> handleClick());
        }
        private void handleClick()
        {
            if (currentPlayer % 2 == 0)
            {
                setPlayer(0);
                currentPlayer++;
            }
            else
            {
                 setPlayer(1);
                 currentPlayer++;
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
                setStyle("-fx-background-color: blue");
            }
            else if (player == 1)
            {
                setStyle("-fx-background-color: red");
            }
        }
    }
    public static void main(String[] args) throws IOException
    {
        final int PORT = 8888;
        ServerSocket server = new ServerSocket(PORT);
        launch(args);
    }
}
