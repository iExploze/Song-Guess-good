package dataAccessObjects;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
    public String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder(this.length);
        for (int i = 0; i < this.length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public void generateSHA256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to base64 representation
            this.hash = Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public String getHash() {
        return this.hash;
    }



    public static void main(String[] args) {// Specify the desired length of the random string. Should be btwn 43 and 128.
        RandomSecureS256Generator string = new RandomSecureS256Generator(43);
        string.generateSHA256Hash(string.generateRandomString());
        System.out.println("Random String: " + string.getHash());
    }
}