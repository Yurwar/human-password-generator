package com.yurwar.hash;

import org.apache.commons.codec.binary.Base16;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Random;

public class Sha1HashingStrategy extends AbstractHashingStrategy {
    private static final Random random = new SecureRandom();
    private static final Base16 base16 = new Base16(true);

    @Override
    protected String hash(String password) {
        final byte[] saltBytes = new byte[20];
        random.nextBytes(saltBytes);
        final String salt = base16.encodeAsString(saltBytes);

        final String saltedPassword = DigestUtils.sha1Hex(password.concat(salt));

        return saltedPassword + "," + salt;
    }
}
