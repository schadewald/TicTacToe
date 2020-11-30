import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;

public class Main extends Application
{
    GridPane gridPaneOne = new GridPane();
    GridPane gridPaneTwo = new GridPane();
    Cell[][] cellOne = new Cell[3][3];
    Cell[][] cellTwo = new Cell[3][3];
    private int currentPlayer = 0;
    @Override
    public void start(Stage playerOneStage) throws Exception
    {
        for (int i = 0; i < 3; i ++)
        {
            for (int j = 0; j < 3; j++)
            {
                cellOne[i][j] = new Cell();
                gridPaneOne.add(cellOne[i][j], j, i);
                cellTwo[i][j] = new Cell();
                gridPaneTwo.add(cellTwo[i][j], j, i);
            }
        }
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
            cellTwo[0][1].setPlayer(0);
            if (player == 8)
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
                if (hasWon(this.player))
                {
                    System.out.println("Player " + player + " has won!");
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
        public void setCell(int p)
        {

        }
    }
    public static void main(String[] args) throws IOException
    {
        final int PORT = 8888;
        ServerSocket server = new ServerSocket(PORT);
        launch(args);
    }
}