package com.yurwar.utils;

import com.yurwar.files.AbstractFileReader;
import com.yurwar.files.DefaultFileReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class GeneratorUtils {
    public static final String VOWELS = "aeiouy";
    public static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_NUM_SYMBOLS = ")!@#$%^&*(";
    public static final Map<String, String> NUM_TO_UPPERCASE_SYMBOL = createNumToUppercaseSymbol();
    public static final String DELIMITERS = "-_.";
    public static final String OTHER_SYMBOLS = ";:?*=,";
    private static final AbstractFileReader<String> defaultFileReader = new DefaultFileReader();
    public static final List<String> TOP_25_PASSWORDS =
            defaultFileReader.readPasswordsFromFile("./src/main/resources/top25_passwords.csv");

    private static Map<String, String> createNumToUppercaseSymbol() {
        return IntStream.range(0, 10)
                .mapToObj(num -> Map.entry(String.valueOf(num), UPPERCASE_NUM_SYMBOLS.split("")[num]))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private GeneratorUtils() {}

}
