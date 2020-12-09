package com.yurwar.hash;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BCryptHashingStrategy extends AbstractHashingStrategy {

    private static final int DEFAULT_COST = 12;

    @Override
    protected String hash(String password) {
        return BCrypt.withDefaults().hashToString(DEFAULT_COST, password.toCharArray());
    }
}
