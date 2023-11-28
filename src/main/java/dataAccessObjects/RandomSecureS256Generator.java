package dataAccessObjects;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


/*Generates S256 hash */
public class RandomSecureS256Generator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    private int length;
    private String hash;

    public RandomSecureS256Generator(int length) {
        this.length = length;
        this.hash = null;
    }


    public String generateRandomCodeChallenge(String CodeVerifier) {

        String base64Encoded = base64encode(sha256(CodeVerifier));
        return base64Encoded;
    }
    public String generateRandomCodeVerifier(int length) {
        return generateRandomString(length);
    }
    private static String generateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);

        StringBuilder sb = new StringBuilder();
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (byte b : randomBytes) {
            sb.append(possible.charAt(Math.abs(b) % possible.length()));
        }

        return sb.toString();
    }

    // Function to calculate SHA-256 hash
    private static byte[] sha256(String plain) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plain.getBytes());
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to perform Base64 encoding with URL-safe characters
    private static String base64encode(byte[] input) {
        String base64Encoded = Base64.getEncoder().encodeToString(input)
                .replace("+", "-")
                .replace("/", "_")
                .replace("=", "");
        return base64Encoded;
    }



    public String getHash() {
        return this.hash;
    }



    public static void main(String[] args) {// Specify the desired length of the random string. Should be btwn 43 and 128.
        RandomSecureS256Generator string = new RandomSecureS256Generator(43);

    }
}