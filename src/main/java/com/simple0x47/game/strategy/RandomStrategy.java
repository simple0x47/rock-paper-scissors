package com.simple0x47.game.strategy;

import com.simple0x47.game.Action;

/**
 * Players select their action depending on a random factor.
 */
public class RandomStrategy implements PlayStrategy {
    private static RandomStrategy instance;

    private RandomStrategy() {

    }

    public static PlayStrategy getInstance() {
        if (instance == null) {
            instance = new RandomStrategy();
        }

        return instance;
    }

    @Override
    public Action.ActionType doAction() {
        double decision = Math.random();

        if (decision < 0.33) {
            return Action.ActionType.ROCK;
        } else if (decision < 0.66) {
            return Action.ActionType.SCISSORS;
        } else {
            return Action.ActionType.PAPER;
        }
    }
}
