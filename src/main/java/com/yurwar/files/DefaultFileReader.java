package com.yurwar.files;

public class DefaultFileReader extends AbstractFileReader<String> {
    @Override
    protected String parsePassword(String plainPassword) {
        return plainPassword;
    }
}
