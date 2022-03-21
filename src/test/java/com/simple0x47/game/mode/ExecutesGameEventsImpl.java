package com.simple0x47.game.mode;

import com.simple0x47.game.Action;
import com.simple0x47.game.Player;
import com.simple0x47.game.events.GameEvents;

public class ExecutesGameEventsImpl implements GameEvents {
    public boolean beforeTriggered;
    public boolean afterTriggered;

    @Override
    public void beforeIterate(Player player1, Player player2) {
        beforeTriggered = true;
    }

    @Override
    public void afterIterate(Player player1, Action.ActionType player1Action, Player player2, Action.ActionType player2Action) {
        afterTriggered = true;
    }
}
