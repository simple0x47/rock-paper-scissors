package com.simple0x47.game;

import com.simple0x47.game.events.GameEvents;
import com.simple0x47.game.events.IOLocalModeGameEvents;
import com.simple0x47.game.mode.GameMode;
import com.simple0x47.game.mode.LocalMode;

/**
 * Entry point for the rock paper scissors game.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("###:::--- ROCK ||| PAPER ||| SCISSORS ---:::###");

        IOController ioController = new IOController();

        Player player1 = new Player();
        Player player2 = new Player();

        String mode = ioController.askAndApplyModeOfPlay(player1, player2, true);

        IOLocalModeGameEvents ioGameEvents = new IOLocalModeGameEvents(ioController);

        GameMode gamemode;
        if (mode.equalsIgnoreCase(IOController.MODE_OF_PLAY_REMOTE)) {
            String hostOrClient = ioController.askHostOrClient();
            RemoteExtension remoteExtension = new RemoteExtension();

            if (hostOrClient.equalsIgnoreCase(IOController.REMOTE_HOST)) {
                gamemode = remoteExtension.initializeHost(player1, player2, new GameEvents[]{ioGameEvents});
            } else {
                gamemode = remoteExtension.initializeClient(ioController);
            }
        } else {
            gamemode = new LocalMode(player1, player2, new GameEvents[]{ioGameEvents});
        }

        gamemode.start();
    }
}
