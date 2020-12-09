package com.yurwar.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractFileReader<T> {
    public List<T> readPasswordsFromFile(String filename) {
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
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
