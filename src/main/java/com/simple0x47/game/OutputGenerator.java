package com.simple0x47.game;

public class OutputGenerator {
    public static String generateOutputForActions(Action.ActionType player1Action, Action.ActionType player2Action) {
        StringBuilder actionsOutput = new StringBuilder();

        generateOutputForPlayerAction(actionsOutput, player1Action, 1);
        generateOutputForPlayerAction(actionsOutput, player2Action, 2);

        generateOutputForResult(actionsOutput, player1Action, player2Action);

        return actionsOutput.toString();
    }

    private static void generateOutputForPlayerAction(StringBuilder actionsOutput,
                                                      Action.ActionType playerAction, int playerNumber) {
        actionsOutput.append("Player " + playerNumber + " played ");
        actionsOutput.append(playerAction.toString());
        actionsOutput.append(".");
        actionsOutput.append("\n");
    }

    private static void generateOutputForResult(StringBuilder actionsOutput,
                                                Action.ActionType player1Action, Action.ActionType player2Action) {
        int comparison = Action.compare(player1Action, player2Action);

        if (comparison < 0) {
            actionsOutput.append("\n");
            actionsOutput.append("### PLAYER 2 WINS ###");
            actionsOutput.append("\n");
        } else if (comparison == 0) {
            actionsOutput.append("\n");
            actionsOutput.append("### TIE ###");
            actionsOutput.append("\n");
        } else {
            actionsOutput.append("\n");
            actionsOutput.append("### PLAYER 1 WINS ###");
            actionsOutput.append("\n");
        }
    }

    public static String generateOutputForStats(Player player1, Player player2) {
        StringBuilder statsOutput = new StringBuilder();

        generateOutputForPlayerStats(statsOutput, player1, 1);

        statsOutput.append("\n");

        generateOutputForPlayerStats(statsOutput, player2, 2);

        statsOutput.append("\n");

        return statsOutput.toString();
    }

    private static void generateOutputForPlayerStats(StringBuilder statsOutput, Player player, int playerNumber) {
        statsOutput.append("\n");
        statsOutput.append("### [PLAYER " + playerNumber + "] ###");
        statsOutput.append("\n");
        statsOutput.append("Won: ");
        statsOutput.append(player.getStats().getWonMatches());
        statsOutput.append("\n");
        statsOutput.append("Tied: ");
        statsOutput.append(player.getStats().getTiedMatches());
        statsOutput.append("\n");
        statsOutput.append("Lost: ");
        statsOutput.append(player.getStats().getLostMatches());
        statsOutput.append("\n");
    }
}
