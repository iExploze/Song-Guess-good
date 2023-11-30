package dataAccessObjects.UserStorage;

import entities.Users.User;
import entities.Users.UserFactory;
import usecase.Login.LoginUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FileUserDataAccessObject implements UserDataAccessObject, LoginUserDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("access_token", 2);
        headers.put("refresh_token", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // TODO clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,access_token");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String accessToken = String.valueOf(col[headers.get("access_token")]);
                    String refreshToken = String.valueOf(col[headers.get("refresh_token")]);
                    HashMap <String, String> tokenInfo = new HashMap<>();
                    tokenInfo.put("access_token", accessToken);
                    tokenInfo.put("refresh_token", refreshToken);
                    User user = userFactory.createUser(username, password, tokenInfo);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public boolean exists(String username) {
        return accounts.containsKey(username);
    }

    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }


    public User getUser(String username) {
        return accounts.get(username);
    }

    public void setTokenInfo(User user) { //assume updated user.
         //get user and update access token
        accounts.put(user.getUsername(), user); //reupdate the user
        updateTokenInfo(user.getUsername(), user.getAccessToken(), user.getRefreshToken());
    }
    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s,%s".formatted(
                        user.getUsername(), user.getPassword(), user.getAccessToken(), user.getRefreshToken());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (Exception ignored){

        };



    }

    private void updateTokenInfo(String username, String accessToken, String refreshToken) {



        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {

            while (reader.ready()) {
                String line = reader.readLine();
                String[] parts = line.split(","); // Assuming CSV format

                // Check if the username matches the one to be modified
                if (parts.length >= 4 && parts[0].trim().equals(username)) {
                    // Modify the access token
                    parts[2] = accessToken;
                    parts[3] = refreshToken;
                }

                // Write the modified or unmodified line to the new file
                writer.write(String.join(",", parts));
                writer.newLine(); // Add a newline character after each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the original file
        File originalFile = new File(csvFile.getName());
        if (!originalFile.delete()) {
            System.out.println("Error deleting the original file.");
            return;
        }
        File tempFile = new File("temp.txt");
        // Rename the temporary file to the original file
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Access token modified successfully.");
        } else {
            System.out.println("Error modifying the access token.");
        }
    }
}
