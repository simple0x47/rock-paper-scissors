package com.simple0x47.game.events;

import com.simple0x47.game.Action;
import com.simple0x47.game.Player;

public interface GameEvents {
    void beforeIterate(Player player1, Player player2);
    void afterIterate(Player player1, Action.ActionType player1Action, Player player2, Action.ActionType player2Action);
}
