package com.simple0x47.game.strategy;

import com.simple0x47.game.Action;

/**
 * Strategy that a player follows through the iterations of the game.
 */
public interface PlayStrategy {
    Action.ActionType doAction();
}
