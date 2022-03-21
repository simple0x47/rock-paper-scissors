package com.simple0x47.game.mode;

import com.simple0x47.game.Action;
import com.simple0x47.game.Player;
import com.simple0x47.game.events.GameEvents;

public class ExpectedAmountOfTimesGameEventsImpl implements GameEvents {
    public int beforeCount;
    public int afterCount;

    @Override
    public void beforeIterate(Player player1, Player player2) {
        beforeCount++;
    }

    @Override
    public void afterIterate(Player player1, Action.ActionType player1Action, Player player2, Action.ActionType player2Action) {
        afterCount++;
    }
}
