package com.yurwar.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5HashingStrategy extends AbstractHashingStrategy {
    @Override
    protected String hash(String password) {
        return DigestUtils.md5Hex(password);
    }
}
