package com.simple0x47.game;

/**
 * Utility class that provides a single-point access to a player's stats.
 */
public class Stats {
    private int wonMatches;
    private int tiedMatches;
    private int lostMatches;

    public Stats() {

    }

    public int getWonMatches() {
        return wonMatches;
    }

    public void addWin() {
        wonMatches++;
    }

    public int getTiedMatches() {
        return tiedMatches;
    }

    public void addTie() {
        tiedMatches++;
    }

    public int getLostMatches() {
        return lostMatches;
    }

    public void addLoss() {
        lostMatches++;
    }

    public void reset() {
        wonMatches = 0;
        lostMatches = 0;
    }
}
