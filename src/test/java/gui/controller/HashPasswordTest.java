package gui.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;

/**
 * Test class for HashPassword.
 */
class HashPasswordTest {


    @Test
    void hashPasswordEquals1234() throws NoSuchAlgorithmException {
        final HashPassword password = new HashPassword("1234");
        assertEquals("81dc9bdb52d04dc2036dbd8313ed055", password.hashPassword(), "Expected and Actual results are equal.");
    }

}