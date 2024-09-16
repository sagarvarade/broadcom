package com.util;

import org.junit.Test;

public class EncryptionUtilTest {

    @Test
    public void EncryptUtilTest() throws Exception {
        String originalString = "Hello, World!";

        // Encrypt the string
        String encryptedString = EncryptionUtil.encrypt(originalString);
        System.out.println("Encrypted String: " + encryptedString);

        // Decrypt the string
        String decryptedString = EncryptionUtil.decrypt(encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}
