package com.innowise.task4.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class EncoderSHA256  {

    public static String encode(String password) {
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}
