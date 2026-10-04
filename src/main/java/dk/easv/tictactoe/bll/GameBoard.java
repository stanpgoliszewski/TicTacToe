
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    private int currentPlayer = 0;
    private final int[][] board = new int[3][3]; // -1 = empty
    private boolean gameOver = false;
    private int winner = -1;

    public int getNextPlayer() {
        return currentPlayer;          // no mutation
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row) {
        if (gameOver || board[row][col] != 0) return false;
        board[row][col] = currentPlayer;
        // TODO: check win → set winner + gameOver = true
        // TODO: check draw (board full) → gameOver = true, winner = -1
        if (!gameOver) currentPlayer = 1 - currentPlayer;
        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true iasaf the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        for (int[] r : board) java.util.Arrays.fill(r, -1);
        currentPlayer = 0;
        gameOver = false;
        winner = -1;
    }
}
