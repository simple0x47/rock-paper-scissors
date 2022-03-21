package com.simple0x47.game.strategy;

import com.simple0x47.game.Action;

/**
 * Unfair strategy method, where the player plays a specific action type.
 */
public class FixedStrategy implements PlayStrategy {
    private static FixedStrategy instance;

    private FixedStrategy() {

    }

    public static PlayStrategy getInstance() {
        if (instance == null) {
            instance = new FixedStrategy();
        }

        return instance;
    }

    @Override
    public Action.ActionType doAction() {
        return Action.ActionType.ROCK;
    }
}
