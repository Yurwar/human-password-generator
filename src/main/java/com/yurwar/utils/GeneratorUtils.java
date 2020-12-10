package com.yurwar.utils;

import com.yurwar.files.reader.DefaultPasswordsReader;
import com.yurwar.files.reader.PasswordReader;

import java.util.List;

public final class GeneratorUtils {

    public static final String VOWELS = "aeiouy";
    public static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_NUM_SYMBOLS = ")!@#$%^&*(";
    public static final String DELIMITERS = "-_.";
    public static final String OTHER_SYMBOLS = ";:?*=,";
    public static final String NUMBERS = "0123456789";
    private static final PasswordReader<String> defaultFileReader = new DefaultPasswordsReader();
    public static final List<String> TOP_25_PASSWORDS =
            defaultFileReader.readPasswordsFromFile("top25_passwords.csv");
    public static final List<String> TOP_100K_PASSWORDS =
            defaultFileReader.readPasswordsFromFile("top100k_passwords.csv");
    public static final List<String> WORD_DICTIONARY =
            defaultFileReader.readPasswordsFromFile("word_dictionary.csv");

    private GeneratorUtils() {}

}
