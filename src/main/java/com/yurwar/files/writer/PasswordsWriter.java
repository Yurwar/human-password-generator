package com.yurwar.files.writer;

import java.util.List;

public interface PasswordsWriter<T> {
    void writePasswordsToFile(List<T> passwords, String filename);

    void writePasswordsToFile(List<T> passwords, String pathToFile, String filename);
}
