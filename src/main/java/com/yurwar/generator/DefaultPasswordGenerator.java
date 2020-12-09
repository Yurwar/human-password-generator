package com.yurwar.generator;

import com.yurwar.hash.AbstractHashingStrategy;
import com.yurwar.hash.BCryptHashingStrategy;
import com.yurwar.hash.Md5HashingStrategy;
import com.yurwar.hash.Sha1HashingStrategy;
import com.yurwar.utils.GeneratorUtils;

import java.util.List;

public class DefaultPasswordGenerator implements PasswordGenerator {
    private static final int AMOUNT_OF_PASSWORDS = 1_000_000;
    private static final double TOP_25_PASSWORDS_PERCENTAGE = 0.05;
    private static final double MOST_COMMON_PASSWORDS_PERCENTAGE = 0.8;
    private static final double RANDOM_PASSWORD_PERCENTAGE = 0.05;
    private static final double REST_PASSWORDS = 0.1;

    private final List<AbstractHashingStrategy> hashingStrategies;

    public DefaultPasswordGenerator() {
        hashingStrategies = List.of(new Md5HashingStrategy(), new Sha1HashingStrategy(), new BCryptHashingStrategy());
    }

    @Override
    public void generate() {
        System.out.println(GeneratorUtils.TOP_25_PASSWORDS);
    }
}
