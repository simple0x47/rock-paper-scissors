package com.simple0x47.game;

import com.simple0x47.game.events.GameEvents;
import com.simple0x47.game.mode.GameMode;
import com.simple0x47.game.mode.RemoteClientMode;
import com.simple0x47.game.mode.RemoteHostMode;

import java.net.URL;

/**
 * Facade for initializing the host or client mode. Thus, allowing to avoid future network related details from
 * infecting the Main class.
 */
public class RemoteExtension {
    public GameMode initializeHost(Player player1, Player player2, GameEvents[] gameEvents) {
        return new RemoteHostMode(player1, player2, gameEvents);
    }

    public GameMode initializeClient(IOController ioController) {
        URL hostUrl = ioController.askHostUrl();

        return new RemoteClientMode(hostUrl, ioController);
    }
}
