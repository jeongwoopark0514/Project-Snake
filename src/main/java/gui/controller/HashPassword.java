package gui.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer stringbuffer = new StringBuffer();
        for(byte b1 : b) {
            stringbuffer.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return stringbuffer.toString();
    }
}
