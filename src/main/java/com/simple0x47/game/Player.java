package com.simple0x47.game;

import com.simple0x47.game.strategy.PlayStrategy;
import com.simple0x47.game.strategy.RandomStrategy;

public class Player {

    private Stats stats;
    private PlayStrategy strategy;

    public Player() {
        stats = new Stats();
        strategy = RandomStrategy.getInstance();
    }

    public Player(PlayStrategy strategy) {
        stats = new Stats();
        this.strategy = strategy;
    }

    public Stats getStats() {
        return stats;
    }

    public Action.ActionType doAction() {
        return strategy.doAction();
    }

    public void updateStrategy(PlayStrategy strategy) {
        this.strategy = strategy;
    }
}
