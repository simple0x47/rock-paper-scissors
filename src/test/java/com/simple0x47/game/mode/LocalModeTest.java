package com.simple0x47.game.mode;

import com.simple0x47.game.Player;
import com.simple0x47.game.Stats;
import com.simple0x47.game.events.GameEvents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalModeTest {
    @Test
    public void updatesPlayerStats() {
        final int expectedIterations = GameMode.ITERATIONS_TO_BE_PLAYED;

        Player player1 = new Player();
        Stats player1Stats = player1.getStats();
        Player player2 = new Player();
        Stats player2Stats = player2.getStats();

        GameEvents[] gameEvents = new GameEvents[]{};

        LocalMode localMode = new LocalMode(player1, player2, gameEvents);


        localMode.start();


        int player1Iterations = player1Stats.getLostMatches() + player1Stats.getTiedMatches() +
                player1Stats.getWonMatches();
        int player2Iterations = player2Stats.getLostMatches() + player2Stats.getTiedMatches() +
                player2Stats.getWonMatches();

        Assertions.assertEquals(expectedIterations, player1Iterations);
        Assertions.assertEquals(expectedIterations, player2Iterations);
    }

    @Test
    public void executesGameEvents() {
        Player player1 = new Player();
        Player player2 = new Player();

        ExecutesGameEventsImpl executesGameEvents = new ExecutesGameEventsImpl();

        GameEvents[] gameEvents = new GameEvents[]{executesGameEvents};

        LocalMode localMode = new LocalMode(player1, player2, gameEvents);


        localMode.start();


        Assertions.assertTrue(executesGameEvents.beforeTriggered);
        Assertions.assertTrue(executesGameEvents.afterTriggered);
    }

    @Test
    public void executesBeforeAndAfterIterationExpectedAmountOfTimes() {
        final int expectedBeforeTriggeredCount = GameMode.ITERATIONS_TO_BE_PLAYED - 1;
        final int expectedAfterTriggeredCount = GameMode.ITERATIONS_TO_BE_PLAYED;

        Player player1 = new Player();
        Player player2 = new Player();

        ExpectedAmountOfTimesGameEventsImpl executesGameEvents = new ExpectedAmountOfTimesGameEventsImpl();

        GameEvents[] gameEvents = new GameEvents[]{executesGameEvents};

        LocalMode localMode = new LocalMode(player1, player2, gameEvents);


        localMode.start();


        Assertions.assertEquals(expectedBeforeTriggeredCount, executesGameEvents.beforeCount);
        Assertions.assertEquals(expectedAfterTriggeredCount, executesGameEvents.afterCount);
    }
}
