package com.yurwar.files.reader;

public class DefaultPasswordsReader extends AbstractPasswordsReader<String> {
    @Override
    protected String parsePassword(String plainPassword) {
        return plainPassword;
    }
}
