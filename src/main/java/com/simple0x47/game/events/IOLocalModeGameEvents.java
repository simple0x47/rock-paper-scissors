package com.simple0x47.game.events;

import com.simple0x47.game.Action;
import com.simple0x47.game.IOController;
import com.simple0x47.game.Player;

public class IOLocalModeGameEvents implements GameEvents {
    private final IOController ioController;

    public IOLocalModeGameEvents(IOController ioController) {
        this.ioController = ioController;
    }

    @Override
    public void beforeIterate(Player player1, Player player2) {
        ioController.askAndApplyModeOfPlay(player1, player2, false);
    }

    @Override
    public void afterIterate(Player player1, Action.ActionType player1Action,
                             Player player2, Action.ActionType player2Action ) {
        ioController.askAndApplyOutputMode();
        ioController.outputEndOfIteration(player1, player1Action, player2, player2Action);
    }
}
