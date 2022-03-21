package com.simple0x47.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatsTest {
    private Stats stats;

    @BeforeEach
    public void setUp() {
        stats = new Stats();
    }

    @Test
    public void addsWinsCorrectly() {
        final int expectedWins = 3;

        for (int i = 0; i < expectedWins; i++) {
            stats.addWin();
        }

        Assertions.assertEquals(expectedWins, stats.getWonMatches());
    }

    @Test
    public void addsTiesCorrectly() {
        final int expectedTies = 6;

        for (int i = 0; i < expectedTies; i++) {
            stats.addTie();
        }

        Assertions.assertEquals(expectedTies, stats.getTiedMatches());
    }

    @Test
    public void addsLossesCorrectly() {
        final int expectedLosses = 5;

        for (int i = 0; i < expectedLosses; i++) {
            stats.addLoss();
        }

        Assertions.assertEquals(expectedLosses, stats.getLostMatches());
    }
}
