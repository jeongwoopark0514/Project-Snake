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
        String originPassword = "password1";
        PasswordHash phash = new PasswordHash(originPassword);
        String generatedHash =  phash.createHash();
        boolean result = phash.validatePassword(generatedHash);
        assertTrue(result, "Two same passwords are recognized as the same passwords.");
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