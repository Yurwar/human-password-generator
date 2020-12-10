package com.yurwar.generator;

import com.yurwar.hash.AbstractHashingStrategy;
import com.yurwar.hash.BCryptHashingStrategy;
import com.yurwar.hash.Md5HashingStrategy;
import com.yurwar.hash.Sha1HashingStrategy;
import com.yurwar.utils.GeneratorUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultPasswordGenerator implements PasswordGenerator {
    private static final int AMOUNT_OF_PASSWORDS = 1_000_000;
    private static final double TOP_25_PASSWORDS_PERCENTAGE = 0.05;
    private static final double MOST_COMMON_PASSWORDS_PERCENTAGE = 0.8;
    private static final double RANDOM_PASSWORD_PERCENTAGE = 0.05;
    private static final double REST_PASSWORDS = 0.1;
    private static final Random random = new SecureRandom();

    private final List<AbstractHashingStrategy> hashingStrategies;

    public DefaultPasswordGenerator() {
        hashingStrategies = List.of(new Md5HashingStrategy(), new Sha1HashingStrategy(), new BCryptHashingStrategy());
    }

    @Override
    public void generate() {
        System.out.println(GeneratorUtils.TOP_25_PASSWORDS);
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
                    final int symbolIndex = random.nextInt(symbols.length);
                    charToAppend = symbols[symbolIndex];
                } else {
                    if (j % 2 == 0) {
                        final int consonantIndex = random.nextInt(GeneratorUtils.CONSONANTS.length());
                        charToAppend = GeneratorUtils.CONSONANTS.split("")[consonantIndex];
                    } else {
                        final int vowelIndex = random.nextInt(GeneratorUtils.VOWELS.length());
                        charToAppend = GeneratorUtils.VOWELS.split("")[vowelIndex];
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
}
