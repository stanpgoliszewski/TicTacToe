package dk.easv.tictactoe.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAI
{
    private final Random rnd = new Random();

    /* returns row and column of a free cell, or null if the board is full. */
    public int[] chooseMove(IGameBoard game)
    {
        List<int[]> free = new ArrayList<>();
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (game.getCell(c, r) == -1)
                    free.add(new int[]{c, r});

        if (free.isEmpty()) return null;
        return free.get(rnd.nextInt(free.size()));
    }
}