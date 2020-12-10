package com.yurwar.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5HashingStrategy extends AbstractHashingStrategy {

    private static final String NAME = "md5";

    @Override
    protected String hash(String password) {
        return DigestUtils.md5Hex(password);
    }

    @Override
    public String getName() {

        return NAME;
    }
}
