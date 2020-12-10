package com.yurwar.files.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPasswordsReader<T> implements PasswordReader<T> {
    protected static final String DEFAULT_PATH_TO_FILE = "./src/main/resources/";

    @Override
    public List<T> readPasswordsFromFile(String filename) {
        return readPasswordsFromFile(DEFAULT_PATH_TO_FILE, filename);
    }

    @Override
    public List<T> readPasswordsFromFile(String pathToFile, String filename) {
        try (Stream<String> lines = Files.lines(Path.of(pathToFile + filename))) {
            return lines
                    .map(this::parsePassword)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Can not read file: " + filename);
            return Collections.emptyList();
        }
    }

    protected abstract T parsePassword(String plainPassword);
}
