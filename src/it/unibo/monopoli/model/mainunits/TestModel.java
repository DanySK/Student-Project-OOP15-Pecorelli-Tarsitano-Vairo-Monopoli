package it.unibo.monopoli.model.mainunits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * This is the class officer to the model's test.
 *
 */
public class TestModel {

    private static final int PAWN_ID_AND_N_PLAYER_1 = 1;
    private static final int PAWN_ID_AND_N_PLAYER_2 = 2;
    private static final int PAWN_ID_AND_N_PLAYER_3 = 3;
    private static final int PAWN_ID_AND_N_PLAYER_4 = 4;
    private static final int PAWN_ID_AND_N_PLAYER_5 = 5;
    private static final int PAWN_ID_AND_N_PLAYER_6 = 6;

    private static final int MONEY_5_PLAYERS = 200;

    private static final int OWNERSHIP_5_PLAYERS = 4;
    
    private static final int N_ALL_BOXES = 40;

    /**
     * This is the test for 5 platers.
     */
    @Test
    public void test5Players() {
        final List<Player> players;
        players = new LinkedList<>();
        players.add(new ClassicPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true));
        players.add(new ClassicPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true));
        players.add(new ClassicPlayer("Giuseppe", new ClassicPawn(PAWN_ID_AND_N_PLAYER_3), true));
        players.add(new ClassicPlayer("Computer1", new ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false));
        players.add(new ClassicPlayer("Computer2", new ClassicPawn(PAWN_ID_AND_N_PLAYER_5), false));
        final GameVersion version = new GameVersionImpl(new ClassicStrategy(players));

        assertSame(players.size(), PAWN_ID_AND_N_PLAYER_5);

        players.forEach(p -> {
            assertEquals(p.getMoney(), MONEY_5_PLAYERS);
//            System.out.println(p.getOwnerships().size());
            assertEquals(p.getOwnerships().size(), OWNERSHIP_5_PLAYERS);
        });

        assertEquals(version.getNextPlayer(), players.get(0));
        assertEquals(version.getNextPlayer(), players.get(1));
        assertEquals(version.getNextPlayer(), players.get(2));
        assertEquals(version.getNextPlayer(), players.get(3));
        assertEquals(version.getNextPlayer(), players.get(4));
        assertEquals(version.getNextPlayer(), players.get(0));
        
        assertEquals(version.getAllBoxes().size(), N_ALL_BOXES);
  }

}
