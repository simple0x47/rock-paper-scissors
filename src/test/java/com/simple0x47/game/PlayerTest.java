package com.simple0x47.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private static final int SAFE_ITERATION_CHECK = 10;

    @Test
    public void playsUpdatedStrategy() {
        Player player = new Player();


        player.updateStrategy(() -> Action.ActionType.SCISSORS);


        Assertions.assertEquals(Action.ActionType.SCISSORS, player.doAction());
        // Execute many times to avoid possible randomness caused by RandomStrategy.
        for (int i = 0; i < SAFE_ITERATION_CHECK; i++) {
            Assertions.assertEquals(Action.ActionType.SCISSORS, player.doAction());
        }
    }
}
