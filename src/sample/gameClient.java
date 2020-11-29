package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class gameClient extends Application implements Runnable
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("in start.");
    }
    @Override
    public void run()
    {
        GridPane gridPane = new GridPane();
        System.out.println("Game Client Called.");
        Stage newStage = new Stage();
        newStage.setTitle("Testing");
        Scene mainScene = new Scene(gridPane);
        newStage.setScene(mainScene);
        newStage.show();

    }
}
