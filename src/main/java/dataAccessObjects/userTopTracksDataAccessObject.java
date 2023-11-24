package dataAccessObjects;

import com.google.gson.Gson;
import entities.Users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class userTopTracksDataAccessObject implements userTrackandPlaylistDataAccessObject {

    private User user;
    public userTopTracksDataAccessObject(User user) {
        this.user = user;
    }
    public class NeedRefreshException extends Exception {
        public NeedRefreshException(String message) {
            super(message);
        }
    }

    @Override
    public HashMap getTopTracks() throws IOException, NeedRefreshException {
        URL url = new URL("https://api.spotify.com/v1/me/top/tracks");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setRequestMethod("GET");
        connection.addRequestProperty("Authorization", "Bearer " + this.user.getAccessToken()); //Access token

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response into a BufferedReader
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                // Create a Gson instance
                Gson gson = new Gson();

                // Use Gson to parse the JSON data into your Java object
                HashMap result = gson.fromJson(reader, HashMap.class);

                // Now 'result' contains the data from the JSON file
                System.out.println(result);
                return result;
            }
        } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {


            throw new NeedRefreshException("Token Expired");
        }

            // refresh access Token then try again
            // UserAccessToken u = new UserAccessToken();
            //            u.getUpdatedAccessToken(user);

        else {
            System.out.println("Error: HTTP request failed with code " + responseCode);
        }
        return null;
    }
}
