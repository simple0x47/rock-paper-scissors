package com.simple0x47.game.mode;

import com.simple0x47.game.Action;
import com.simple0x47.game.events.GameEvents;
import com.simple0x47.game.Player;

/**
 * Local game mode where all the logic is being executed in a single machine.
 */
public class LocalMode implements GameMode {
    private final Player player1;
    private final Player player2;

    private final GameEvents[] gameEvents;

    public LocalMode(Player player1, Player player2, GameEvents[] gameEvents) {
        this.player1 = player1;
        this.player2 = player2;

        this.gameEvents = gameEvents;
    }

    @Override
    public void start() {
        iterate(true);

        for (int i = 1; i < ITERATIONS_TO_BE_PLAYED; i++) {
            iterate(false);
        }
    }

    /**
     * Runs the rock paper scissors game logic.
     * @param isStart whether this is the first iteration
     */
    private void iterate(boolean isStart) {
        if (!isStart) {
            for (GameEvents instance : gameEvents) {
                instance.beforeIterate(player1, player2);
            }
        }

        Action.ActionType player1Action = player1.doAction();
        Action.ActionType player2Action = player2.doAction();

        int comparison = Action.compare(player1Action, player2Action);

        if (comparison < 0) {
            player1.getStats().addLoss();
            player2.getStats().addWin();
        } else if (comparison == 0) {
            player1.getStats().addTie();
            player2.getStats().addTie();
        } else {
            player1.getStats().addWin();
            player2.getStats().addLoss();
        }

        for (GameEvents instance : gameEvents) {
            instance.afterIterate(player1, player1Action, player2, player2Action);
        }
    }
}
