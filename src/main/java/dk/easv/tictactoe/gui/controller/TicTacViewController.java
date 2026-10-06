
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;

    /**
     * Event handler for the grid buttonss
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Button btn = (Button) event.getSource();
            Integer row = GridPane.getRowIndex(btn);
            Integer col = GridPane.getColumnIndex(btn);
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            int player = game.getNextPlayer();      // read BEFORE play()
            if (game.play(c, r))
            {
                btn.setText(player == 0 ? "X" : "O");
                if (game.isGameOver()) {
                    displayWinner(game.getWinner());
                    if(game.getWinner()!= -1) highlightWinner();

                }
                else
                    setPlayer();
            }


        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void highlightWinner(){
        int[][] cells = game.getWinningCells();
        if (cells == null) return;

        for (Node n : gridPane.getChildren())
        {
            if (!(n instanceof Button)) continue;

            Integer row = GridPane.getRowIndex(n);
            Integer col = GridPane.getColumnIndex(n);
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            for (int[] cell : cells)
                if (cell[0] == c && cell[1] == r)
                        n.setStyle("-fx-background-color: lightgreen;");
        }
    }

    /*@FXML
    private void (){

    }*/
    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + (game.getNextPlayer()+1));
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + (winner+1) + " wins!!!";
                break;
        }
        lblPlayer.setText(message);

    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            btn.setStyle("");
        }
    }
}
