package com.simple0x47.game;

import com.simple0x47.game.mode.RemoteHostMode;
import com.simple0x47.game.output.ConsoleOutput;
import com.simple0x47.game.output.FileOutput;
import com.simple0x47.game.output.OutputMode;
import com.simple0x47.game.strategy.FixedStrategy;
import com.simple0x47.game.strategy.RandomStrategy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Input/Output one stop interface for making IO tasks cleaner wherever they must be implemented.
 */
public class IOController {
    private static final String AVAILABLE_OPTIONS_MESSAGE = "(Available options: fair, unfair, remote)";
    private static final String AVAILABLE_OUTPUT_MODES = "(Available options: console, file)";
    private static final String AVAILABLE_REMOTE_MODES = "(Available options: host, client)";

    public static final String MODE_OF_PLAY_FAIR = "fair";
    public static final String MODE_OF_PLAY_UNFAIR = "unfair";
    public static final String MODE_OF_PLAY_REMOTE = "remote";

    public static final String REMOTE_HOST = "host";
    public static final String REMOTE_CLIENT = "client";

    private static final String OUTPUT_DESTINATION_CONSOLE = "console";
    private static final String OUTPUT_DESTINATION_FILE = "file";

    private OutputMode outputMode;

    public OutputMode getOutputMode() {
        return outputMode;
    }

    /**
     *
     * @param player1
     * @param player2
     * @param remoteAvailable
     * @return game mode that has been selected
     */
    public String askAndApplyModeOfPlay(Player player1, Player player2, boolean remoteAvailable) {
        String mode = askForModeOfPlay(remoteAvailable);

        if (mode.equalsIgnoreCase(MODE_OF_PLAY_FAIR)) {
            player1.updateStrategy(RandomStrategy.getInstance());
            player2.updateStrategy(RandomStrategy.getInstance());
        } else if (mode.equalsIgnoreCase(MODE_OF_PLAY_UNFAIR)) {
            player1.updateStrategy(RandomStrategy.getInstance());
            player2.updateStrategy(FixedStrategy.getInstance());
        } else if (mode.equalsIgnoreCase(MODE_OF_PLAY_REMOTE) && remoteAvailable) {
            player1.updateStrategy(RandomStrategy.getInstance());
            player2.updateStrategy(RandomStrategy.getInstance());
        } else {
            System.out.println("Invalid option. Please try again.");
            return askAndApplyModeOfPlay(player1, player2, remoteAvailable);
        }

        return mode;
    }

    private String askForModeOfPlay(boolean remoteAvailable) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Which mode of play do you desire to play? " + generateAvailableOptions(remoteAvailable) + ": ");

        return scanner.nextLine();
    }

    private String generateAvailableOptions(boolean remoteAvailable) {
        return(remoteAvailable) ?
                AVAILABLE_OPTIONS_MESSAGE :
                AVAILABLE_OPTIONS_MESSAGE.replace(", remote", "");
    }

    public void askAndApplyOutputMode() {
        String mode = askForOutputMode();

        if (mode.equalsIgnoreCase(OUTPUT_DESTINATION_CONSOLE)) {
            outputMode = ConsoleOutput.getInstance();
        } else if (mode.equalsIgnoreCase(OUTPUT_DESTINATION_FILE)) {
            outputMode = FileOutput.getInstance();
        } else {
            System.out.println("Invalid option. Please try again.");
            askAndApplyOutputMode();
        }
    }

    private String askForOutputMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which output mode do you desire to use? " + AVAILABLE_OUTPUT_MODES + ": ");

        return scanner.nextLine();
    }

    public String askHostOrClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Host or Client? " + AVAILABLE_REMOTE_MODES + ": ");

        String hostOrClient = scanner.nextLine();

        if (!hostOrClient.equalsIgnoreCase(REMOTE_HOST) && !hostOrClient.equalsIgnoreCase(REMOTE_CLIENT)) {
            System.out.println("Invalid option. Please try again.");
            return askHostOrClient();
        }

        return hostOrClient;
    }

    public URL askHostUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Host URL: ");

        String hostUrl = scanner.nextLine();

        try {
            // Basic URL validation embedded.
            return new URL(hostUrl + RemoteHostMode.PLAY_PATH);
        } catch (MalformedURLException e) {
            return askHostUrl();
        }
    }

    /**
     * Outputs the results of an iteration and the player stats on the active output method.
     * @param player1
     * @param player1Action
     * @param player2
     * @param player2Action
     */
    public void outputEndOfIteration(Player player1, Action.ActionType player1Action,
                                     Player player2, Action.ActionType player2Action) {
        outputResultOfTheIteration(player1Action, player2Action);
        outputStats(player1, player2);
    }

    private void outputResultOfTheIteration(Action.ActionType player1Action, Action.ActionType player2Action) {
        String actionsResult = OutputGenerator.generateOutputForActions(player1Action, player2Action);
        outputMode.write(actionsResult);
    }

    private void outputStats(Player player1, Player player2) {
        String stats = OutputGenerator.generateOutputForStats(player1, player2);
        outputMode.write(stats);
    }
}
