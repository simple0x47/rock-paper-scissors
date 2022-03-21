package com.simple0x47.game.output;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Wrapper for file outputting.
 */
public class FileOutput implements OutputMode {
    private static final String OUTPUT_FILE_DIRECTORY = "./output.txt";
    private static FileOutput instance;

    private File file;
    private final Writer output;

    private FileOutput(String directory) {
        try {
            file = new File(directory);

            if (!file.exists()) {
                boolean success = file.createNewFile();

                if (!success) {
                    throw new IllegalStateException("Failed to create a new file");
                }
            }

            output = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create an instance of FileOutput: " + e.getMessage());
        }
    }

    public static FileOutput getInstance() {
        if (instance == null) {
            instance = new FileOutput(OUTPUT_FILE_DIRECTORY);
        }

        return instance;
    }

    @Override
    public void write(String message) {
        try {
            output.write(message);
            output.flush();
        } catch (IOException e) {
            System.err.println("Failed to write details into output file.");
        }
    }
}
