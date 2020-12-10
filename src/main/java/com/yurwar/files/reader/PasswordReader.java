package com.yurwar.files.reader;

import java.util.List;

public interface PasswordReader<T> {
    List<T> readPasswordsFromFile(String filename);

    List<T> readPasswordsFromFile(String pathToFile, String filename);
}
