package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args)
    {
        gameClient testClient = new gameClient();
        gameClient testClient2 = new gameClient();
        Thread thread = new Thread(testClient);
        Thread thread1 = new Thread(testClient2);
        thread.start();
        thread1.start();
    }
}
