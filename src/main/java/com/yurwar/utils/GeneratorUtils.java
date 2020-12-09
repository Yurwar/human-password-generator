package com.yurwar.utils;

import com.yurwar.files.AbstractFileReader;
import com.yurwar.files.DefaultFileReader;

import java.util.List;

public final class GeneratorUtils {
    public static final String VOWELS = "aeiouy";
    public static final String consonants = "bcdfghjklmnpqrstvwxz";
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private static final AbstractFileReader<String> defaultFileReader = new DefaultFileReader();


    public static final List<String> TOP_25_PASSWORDS = defaultFileReader.readPasswordsFromFile("./src/main/resources/top25_passwords.csv");

}
