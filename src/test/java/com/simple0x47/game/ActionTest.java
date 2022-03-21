package com.simple0x47.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionTest {
    @Test
    public void actionComparesLoserAsExpected() {
        Assertions.assertEquals(-1, Action.compare(Action.ActionType.PAPER, Action.ActionType.SCISSORS));
        Assertions.assertEquals(-1, Action.compare(Action.ActionType.SCISSORS, Action.ActionType.ROCK));
        Assertions.assertEquals(-1, Action.compare(Action.ActionType.ROCK, Action.ActionType.PAPER));
    }

    @Test
    public void actionComparesTieAsExpected() {
        Assertions.assertEquals(0, Action.compare(Action.ActionType.PAPER, Action.ActionType.PAPER));
        Assertions.assertEquals(0, Action.compare(Action.ActionType.SCISSORS, Action.ActionType.SCISSORS));
        Assertions.assertEquals(0, Action.compare(Action.ActionType.ROCK, Action.ActionType.ROCK));
    }

    @Test
    public void actionComparesWinAsExpected() {
        Assertions.assertEquals(1, Action.compare(Action.ActionType.SCISSORS, Action.ActionType.PAPER));
        Assertions.assertEquals(1, Action.compare(Action.ActionType.ROCK, Action.ActionType.SCISSORS));
        Assertions.assertEquals(1, Action.compare(Action.ActionType.PAPER, Action.ActionType.ROCK));
    }
}
