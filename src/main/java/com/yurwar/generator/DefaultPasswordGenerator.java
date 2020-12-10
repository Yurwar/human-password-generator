package com.yurwar.generator;

import com.yurwar.files.writer.DefaultPasswordsWriter;
import com.yurwar.files.writer.PasswordsWriter;
import com.yurwar.hash.AbstractHashingStrategy;
import com.yurwar.hash.BCryptHashingStrategy;
import com.yurwar.hash.Md5HashingStrategy;
import com.yurwar.hash.Sha1HashingStrategy;
import com.yurwar.utils.GeneratorUtils;
import com.yurwar.utils.MutationUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DefaultPasswordGenerator implements PasswordGenerator {

    private static final int AMOUNT_OF_PASSWORDS = 1_000_000;
    private static final double TOP_25_PASSWORDS_PERCENTAGE = 0.05;
    private static final double TOP_100K_PASSWORDS_PERCENTAGE = 0.8;
    private static final double RANDOM_PASSWORD_PERCENTAGE = 0.05;
    private static final double REST_PASSWORDS = 0.1;
    private static final Random random = new SecureRandom();
    private static final PasswordsWriter<String> writer = new DefaultPasswordsWriter();

    private final List<AbstractHashingStrategy> hashingStrategies;

    public DefaultPasswordGenerator() {
        hashingStrategies = List.of(new Md5HashingStrategy(), new Sha1HashingStrategy(), new BCryptHashingStrategy());
    }

    @Override
    public void generate() {

        hashingStrategies
                .forEach(this::generateHashes);
    }

    private void generateHashes(final AbstractHashingStrategy strategy) {

        writer.writePasswordsToFile(strategy.hash(generateInternal()), strategy.getName());
    }

    private List<String> generateInternal() {

        return Stream.of(generateFromBestN(GeneratorUtils.TOP_25_PASSWORDS, TOP_25_PASSWORDS_PERCENTAGE),
                generateFromBestN(GeneratorUtils.TOP_100K_PASSWORDS, TOP_100K_PASSWORDS_PERCENTAGE),
                generateRestPasswords((int) (AMOUNT_OF_PASSWORDS * REST_PASSWORDS)),
                generateReallyRandomPasswords((int) (AMOUNT_OF_PASSWORDS * RANDOM_PASSWORD_PERCENTAGE)))

                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private List<String> generateFromBestN(final List<String> passwords, final double percentage) {

        return Stream.of(passwords, generateMutatedPasswords(passwords, percentage))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> generateMutatedPasswords(final List<String> passwords, final double percentage) {

        return IntStream
                .range(BigInteger.ZERO.intValue(), (int) (((AMOUNT_OF_PASSWORDS * percentage) / passwords.size()) - 1))
                .mapToObj(index -> mutatePasswords(passwords))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> mutatePasswords(final List<String> passwords) {

        return passwords.stream()
                .map(MutationUtils::mutatePassword)
                .collect(Collectors.toList());
    }

    private List<String> generateRestPasswords(int size) {
        return random.ints(size, 1, 3)
                .mapToObj(wordsAmount -> IntStream.range(0, wordsAmount)
                        .mapToObj(i -> GeneratorUtils.WORD_DICTIONARY
                                .get(random.nextInt(GeneratorUtils.WORD_DICTIONARY.size())))
                        .collect(Collectors.joining(getRandomCharFromArray(GeneratorUtils.DELIMITERS.split("")))))
                .map(MutationUtils::mutatePassword)
                .collect(Collectors.toList());
    }

    private List<String> generateReallyRandomPasswords(int size) {
        int minLength = 8;
        int maxLength = 16;
        double symbolAppearPercentage = 0.1;
        double uppercasePercentage = 0.2;

        final String[] symbols =
                String.join("", GeneratorUtils.DELIMITERS, GeneratorUtils.OTHER_SYMBOLS,
                        GeneratorUtils.UPPERCASE_NUM_SYMBOLS, GeneratorUtils.NUMBERS).split("");
        final List<String> passwords = new ArrayList<>();
        random.ints(size, minLength, maxLength).forEach(length -> {
            StringBuilder strBuilder = new StringBuilder();
            for (int j = 0; j < length; j++) {
                String charToAppend;
                if (random.nextDouble() <= symbolAppearPercentage) {
                    charToAppend = getRandomCharFromArray(symbols);
                } else {
                    if (j % 2 == 0) {
                        charToAppend = getRandomCharFromArray(GeneratorUtils.CONSONANTS.split(""));
                    } else {
                        charToAppend =
                                getRandomCharFromArray(GeneratorUtils.VOWELS.split(""));
                    }
                }
                if (random.nextDouble() <= uppercasePercentage) {
                    charToAppend = charToAppend.toUpperCase();
                }
                strBuilder.append(charToAppend);
            }
            passwords.add(strBuilder.toString());
        });
        return passwords;
    }

    private String getRandomCharFromArray(String[] symbols) {
        final int symbolIndex = random.nextInt(symbols.length);
        return symbols[symbolIndex];
    }
}
