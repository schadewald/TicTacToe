package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Main extends Application
{
    GridPane gridPane = new GridPane();
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        gameClient testClient = new gameClient();
        gameClient testClient2 = new gameClient();
        Thread thread = new Thread(testClient);
        Thread thread1 = new Thread(testClient2);
        thread.start();
        thread1.start();

        Stage playerOneStage = primaryStage;
        Stage playerTwoStage = new Stage();
        playerOneStage.setTitle("Tic Tac Toe Player 1");
        playerTwoStage.setTitle("Tic Tac Toe Player 2");
        Scene mainScene = new Scene(gridPane);
        playerOneStage.setScene(mainScene);
        playerOneStage.setX(1000);
        playerOneStage.setY(100);
        playerTwoStage.setX(1000);
        playerTwoStage.setY(500);
        playerOneStage.show();
        playerTwoStage.show();
    }
    public static void main(String[] args) throws IOException
    {
        final int PORT = 8888;
        ServerSocket server = new ServerSocket(PORT);
        launch(args);
    }
}
