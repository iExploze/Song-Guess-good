package dataAccessObjects;

import entities.Users.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class APItest implements dataAccessToken{
    /*
    * curently this class is only used to access the api token from spotify if given the correct
    * client id and client secret, IDK if I should put my here for now, but you could try
    *
    * some of the googling I did:
    * https://stackoverflow.com/questions/63876345/how-to-get-access-token-from-spotify-api-java
    * https://developer.spotify.com/documentation/web-api
    * also CHATGPT for any bugs and small fixes
    *
    * how to get a spotify client id and secret here:
    * https://developer.spotify.com/dashboard
    * */
    private static String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static String CLIENT_ID = "ee5a4dc5c931462e9e630c64a8aee5ac";
    private static String CLIENT_SECRET = "1a006c3d6aec4f7e920560c25ddf46ab";

    public String getAccessToken() throws IOException {
        //URL Access Point
        URL url = new URL(TOKEN_URL);

        //Open http connection to URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        //setup post function and request headers
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes()));
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.getOutputStream().write("grant_type=client_credentials".getBytes());

        //get the access token
        Scanner scanner = new Scanner(connection.getInputStream());
        String AccessToken = scanner.next();

        return AccessToken;
    }

    @Override
    public String getAccessToken(User user) throws IOException {
        return null;
    }

    @Override
    public String getUpdatedAccessToken(User user) throws IOException {
        return null;
    }
}
