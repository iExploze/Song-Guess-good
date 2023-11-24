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

    @Override
    public HashMap getTopTracks(User user) throws IOException {
        URL url = new URL("https://api.spotify.com/v1/me/top/tracks");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setRequestMethod("GET");
        connection.addRequestProperty("Authorization", "Bearer " + user.getAccessToken()); //Access token

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
        } else {
            System.out.println("Error: HTTP request failed with code " + responseCode);
        }
        return new HashMap();
    }
}
