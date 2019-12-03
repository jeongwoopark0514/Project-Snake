package gui.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.junit.jupiter.api.Test;


class PasswordHashTest {

    @Test
    void createHashTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String originPassword = "password";
        PasswordHash phash = new PasswordHash(originPassword);
        String generatedHash =  phash.createHash();
        boolean result = phash.validatePassword(generatedHash);
        assertTrue(result, "Two same passwords are recognized as the same passwords.");
    }

    @Test
    void createHashTest2() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String originPassword = "password134343434";
        PasswordHash phash = new PasswordHash(originPassword);
        String generatedHash =  phash.createHash();
        PasswordHash testHash = new PasswordHash("password");
        boolean result = testHash.validatePassword(generatedHash);
        assertFalse(result, "Two same passwords are not recognized as the same passwords.");
    }

    @Test
    void createHashTest3() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String originPassword = "testing";
        PasswordHash phash = new PasswordHash(originPassword);
        String generatedHash =  phash.createHash();
        PasswordHash testHash = new PasswordHash("testing12343434343434");
        boolean result = testHash.validatePassword(generatedHash);
        assertFalse(result, "Two same passwords are not recognized as the same passwords.");
    }

    @Test
    void validatePasswordifPasswordisWrong()
        throws InvalidKeySpecException, NoSuchAlgorithmException {
        String originPassword = "password";
        PasswordHash phash = new PasswordHash(originPassword);
        String generatedHash =  phash.createHash();
        PasswordHash phash2 = new PasswordHash("password1");
        boolean result = phash2.validatePassword(generatedHash);
        assertFalse(result, "Two same passwords are recognized as the same passwords.");
    }
}