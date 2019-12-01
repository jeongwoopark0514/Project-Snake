package gui.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.Getter;

public class HashPassword {
    @Getter
    private String password;


    public HashPassword(String password) {
        this.password = password;
    }

    /**
     * Hash the password for security reason.
     * @return hashed password.
     * @throws NoSuchAlgorithmException when MD5 is not available.
     */
    public String hashPassword() throws NoSuchAlgorithmException {//NOPMD
        MessageDigest mdigest = MessageDigest.getInstance("MD5");
        byte[] passBytes = getPassword().getBytes();
        mdigest.update(passBytes);
        StringBuffer stringbuffer = new StringBuffer();
        for (byte b1 : mdigest.digest()) {
            stringbuffer.append(Integer.toHexString(b1 & 0xff));
        }
        return stringbuffer.toString();
    }

}
