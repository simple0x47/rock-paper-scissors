package com.simple0x47.game;

public class Action {
    /**
     * Enumeration of the possible game's values.
     */
    public enum ActionType {
        PAPER,
        ROCK,
        SCISSORS,
    }

    /**
     *
     * @param firstAction
     * @param secondAction
     * @return -1 if the firstAction loses to the secondAction, 0 if both actions are equal, 1 if the firstAction wins
     * over the secondAction.
     */
    public static int compare(ActionType firstAction, ActionType secondAction) {
        if (firstAction == secondAction) {
            return 0;
        } else if (isAWinnerCase(firstAction, secondAction)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Checks whether the first action wins over the second action.
     * @param firstAction
     * @param secondAction
     * @return true if the first action wins, false otherwise
     */
    private static boolean isAWinnerCase(ActionType firstAction, ActionType secondAction) {
        return (firstAction == ActionType.PAPER && secondAction == ActionType.ROCK) ||
                (firstAction == ActionType.ROCK && secondAction == ActionType.SCISSORS) ||
                (firstAction == ActionType.SCISSORS && secondAction == ActionType.PAPER);
    }
}

