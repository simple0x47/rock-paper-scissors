package com.simple0x47.game.mode;

import com.simple0x47.game.Action;
import com.simple0x47.game.OutputGenerator;
import com.simple0x47.game.Player;
import com.simple0x47.game.events.GameEvents;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;

/**
 * Server side logic of the remote mode.
 */
public class RemoteHostMode implements GameMode {
    private static final int HTTP_SERVER_PORT = 2202;
    private static final int HTTP_SERVER_STOP_DELAY = 0;

    private static final String WAITING_FOR_CLIENT = "Waiting for client...";

    /**
     * Path where the result of the iteration is being given.
     */
    public static final String PLAY_PATH = "/play";

    private HttpServer httpServer;

    private final Player player1;
    private final Player player2;

    private int iteration = 0;
    private GameEvents[] gameEvents;

    public RemoteHostMode(Player player1, Player player2, GameEvents[] gameEvents) {
        this.player1 = player1;
        this.player2 = player2;

        this.gameEvents = gameEvents;
    }

    @Override
    public void start() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(HTTP_SERVER_PORT), 0);
            httpServer.createContext(PLAY_PATH, this::iterate);
            httpServer.setExecutor(null);
            httpServer.start();

            System.out.println(WAITING_FOR_CLIENT);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize HTTPServer.");
        }
    }

    /**
     * Runs an iteration of the rock paper scissors logic and sends the results to the client.
     * @param exchange intercommunication object
     * @throws IOException if the HTTP communication fails
     */
    private void iterate(HttpExchange exchange) throws IOException {
        Action.ActionType player1Action = player1.doAction();
        Action.ActionType player2Action = player2.doAction();

        int comparison = Action.compare(player1Action, player2Action);

        if (comparison < 0) {
            player1.getStats().addLoss();
            player2.getStats().addWin();
        } else if (comparison == 0) {
            player1.getStats().addTie();
            player2.getStats().addTie();
        } else {
            player1.getStats().addWin();
            player2.getStats().addLoss();
        }

        sendResponse(player1, player1Action, player2, player2Action, exchange);

        for (GameEvents instance : gameEvents) {
            instance.afterIterate(player1, player1Action, player2, player2Action);
        }

        iteration++;

        if (iteration >= GameMode.ITERATIONS_TO_BE_PLAYED) {
            httpServer.stop(HTTP_SERVER_STOP_DELAY);
            System.exit(0);
        } else {
            System.out.println(WAITING_FOR_CLIENT);
        }
    }

    /**
     * Prepares the results of the iteration in text format and then sends them through an HTTP Response.
     * @param player1
     * @param player1Action
     * @param player2
     * @param player2Action
     * @param exchange
     * @throws IOException if there is a problem with the connection
     */
    private void sendResponse(Player player1, Action.ActionType player1Action,
                              Player player2, Action.ActionType player2Action, HttpExchange exchange) throws IOException {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append(OutputGenerator.generateOutputForActions(player1Action, player2Action));
        responseBuilder.append(OutputGenerator.generateOutputForStats(player1, player2));
        String response = responseBuilder.toString();

        exchange.sendResponseHeaders(200, response.length());
        Writer writer = new OutputStreamWriter(exchange.getResponseBody());
        writer.write(response);
        writer.close();
    }
}
