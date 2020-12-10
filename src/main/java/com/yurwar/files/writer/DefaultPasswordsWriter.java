package com.yurwar.files.writer;

import com.yurwar.utils.GeneratorUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DefaultPasswordsWriter implements PasswordsWriter<String> {
    private static final String DEFAULT_FILE_PATH = "./src/main/resources/";
    private static final String DEFAULT_FILE_EXTENSION = ".csv";

    @Override
    public void writePasswordsToFile(List<String> passwords, String filename) {
        writePasswordsToFile(passwords, DEFAULT_FILE_PATH, filename);
    }

    @Override
    public void writePasswordsToFile(List<String> passwords, String pathToFile, String filename) {
        filename = filename + DEFAULT_FILE_EXTENSION;
        try {
            Files.write(Path.of(pathToFile + filename), passwords, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while writing to file: " + filename);
        }
    }
}
