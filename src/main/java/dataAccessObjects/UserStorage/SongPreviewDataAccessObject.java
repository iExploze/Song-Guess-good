package dataAccessObjects.UserStorage;
import entities.Users.CommonUser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SongPreviewDataAccessObject {
    public static void main(String[] args) {
        String url = "https://api.spotify.com/v1/albums/{id}/tracks";

     

        String accessToken = new CommonUser().getAccessToken();

        String contentType = "application/json";
        String market = "US";

        try {
            URL apiUrl = new URL(url.replace("{id}", "{album_id}") + "?market=" + market);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", contentType);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String responseData = response.toString();

            } else {
                System.out.println("Request failed with status code " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

