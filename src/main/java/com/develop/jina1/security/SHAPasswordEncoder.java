package com.develop.jina1.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SHAPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        SHA3.DigestSHA3 messageDigest = new SHA3.DigestSHA3(384);
        messageDigest.update(rawPassword.toString().getBytes());
        return Hex.toHexString(messageDigest.digest());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(new DigestUtils("SHA3-384").digestAsHex(rawPassword.toString()));
    }
}
