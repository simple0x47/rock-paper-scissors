package com.simple0x47.game.mode;

import com.simple0x47.game.IOController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Specific game mode which is executed by the client.
 */
public class RemoteClientMode implements GameMode {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;
    private static final int MAX_RETRY = 10;

    private static final String TRYING_AGAIN = "Retrying to connect to host...";

    private final URL url;
    private final IOController ioController;

    private int connectionRetries = 0;
    private int iterationsPlayed;

    public RemoteClientMode(URL hostUrl, IOController ioController) {
        this.url = hostUrl;
        this.ioController = ioController;
    }

    @Override
    public void start() {
        try {
            iterate();
        } catch (IOException e) {
            // Try to reconnect if an IOException has been caught.
            connectionRetries++;

            if (connectionRetries >= MAX_RETRY) {
                throw new IllegalStateException("Failed to connect to host: " + e.getMessage());
            }

            System.out.println(TRYING_AGAIN);
            start();
        }
    }

    /**
     * Runs an iteration simulation expecting the result of the iteration from the server.
     * @throws IOException if the connection failed
     */
    private void iterate() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String receivedLine;

            ioController.askAndApplyOutputMode();

            while ((receivedLine = reader.readLine()) != null) {
                ioController.getOutputMode().write(receivedLine);
                ioController.getOutputMode().write("\n");
            }
            reader.close();

            connection.disconnect();

            iterationsPlayed++;

            if (iterationsPlayed < GameMode.ITERATIONS_TO_BE_PLAYED) {
                iterate();
            } else {
                System.exit(0);
            }
        }
    }
}
