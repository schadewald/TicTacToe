package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class gameClient implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("Game Client Called.");
    }
}
