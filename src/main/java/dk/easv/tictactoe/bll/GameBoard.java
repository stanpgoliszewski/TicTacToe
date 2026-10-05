package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    // 0 = empty, 1 = player 0 (X), 2 = player 1 (O)
    private final int[][] board = new int[3][3];

    private int currentPlayer = 0;   // 0 or 1, as seen by the outside world
    private boolean gameOver = false;
    private int winner = -1;         // -1 = no winner / draw

    /**
     * Returns 0 for player 0, 1 for player 1.
     * Does not change the turn, it only reports whose turn it is.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer()
    {
        return currentPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is successful the turn passes to the next player. If it is
     * refused (cell taken or game over), it stays the same player's turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false.
     */
    @Override
    public boolean play(int col, int row)
    {
        if (gameOver || board[row][col] != 0)
            return false;

        int mark = currentPlayer + 1;   // 1 or 2 in the array
        board[row][col] = mark;

        if (hasWon(mark))
        {
            winner = currentPlayer;
            gameOver = true;
        }
        else if (isBoardFull())
        {
            winner = -1;
            gameOver = true;            // draw
        }
        else
        {
            currentPlayer = 1 - currentPlayer;
        }
        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by a win.
     *
     * @return true if the game is over, else false.
     */
    @Override
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Gets the id of the winner, -1 if it's a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner()
    {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame()
    {
        for (int[] row : board)
            java.util.Arrays.fill(row, 0);

        currentPlayer = 0;
        gameOver = false;
        winner = -1;
    }

    private boolean isBoardFull()
    {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == 0)
                    return false;
        return true;
    }

    private boolean hasWon(int mark)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) return true; // row
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) return true; // column
        }
        return (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)   // diagonal
                || (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);  // anti-diagonal
    }
}