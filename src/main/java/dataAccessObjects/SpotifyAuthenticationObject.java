package dataAccessObjects;


import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class SpotifyAuthenticationObject {

    public HashMap requestAccessTokenfromAuthorization(String authorizationCode, String clientid, String redirect_url, String codeVerifier) throws IOException {
        String TOKEN_URL = "https://accounts.spotify.com/api/token";
        URL url = new URL(TOKEN_URL);
        //Open http connection to URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        //setup post function and request headers

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream outStream = connection.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(String.format("?clientid=%s&grant_type=authorization_code&%s&redirect_uri=%s&code_verifier=%s",
                clientid, authorizationCode, redirect_url, codeVerifier));
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();
        connection.connect();

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
        return null;
    }

    public HashMap getRequestTokenfromRefreshToken(String refreshToken, String clientid) throws IOException {
        String TOKEN_URL = "https://accounts.spotify.com/api/token";
        URL url = new URL(TOKEN_URL);
        //Open http connection to URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        //setup post function and request headers

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream outStream = connection.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(String.format("?grant_type=refresh_token&refresh_token=%s&clientid=%s",
                refreshToken, clientid));
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();
        connection.connect();
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
        return null;
    }


}
