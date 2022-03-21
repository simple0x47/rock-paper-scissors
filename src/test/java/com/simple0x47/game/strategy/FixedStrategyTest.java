package com.simple0x47.game.strategy;

import com.simple0x47.game.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedStrategyTest {
    @Test
    public void alwaysPlaysSameAction() {
        final Action.ActionType expectedAction = Action.ActionType.ROCK;


        PlayStrategy fixedStrategy = FixedStrategy.getInstance();


        Assertions.assertEquals(expectedAction, fixedStrategy.doAction());
    }
}
