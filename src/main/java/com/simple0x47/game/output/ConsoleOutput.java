package com.simple0x47.game.output;

/**
 * Wrapper for console outputting.
 */
public class ConsoleOutput implements OutputMode {
    private static ConsoleOutput instance;

    private ConsoleOutput() {

    }

    public static ConsoleOutput getInstance() {
        if (instance == null) {
            instance = new ConsoleOutput();
        }

        return instance;
    }

    @Override
    public void write(String message) {
        System.out.print(message);
    }
}
