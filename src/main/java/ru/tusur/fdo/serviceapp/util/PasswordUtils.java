package ru.tusur.fdo.serviceapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.util
 * by Oleg Alekseev
 * 03.06.14.
 */
public class PasswordUtils {

    public static String md5(String input){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes(), 0, input.length());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

}
