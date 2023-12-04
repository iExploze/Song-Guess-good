package dataAccessObjects.spotifyAccessObjects;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class SpotifyAuthenticationObject {

    static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }

            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    public String requestAccessTokenfromAuthorization(String authorizationCode, String clientid, String redirect_url, String codeVerifier) {

        // Constructing the request payload
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormDataFromMap(Map.of(
                        "client_id", clientid,
                        "grant_type", "authorization_code",
                        "code", authorizationCode,
                        "redirect_uri", redirect_url,
                        "code_verifier", codeVerifier
                )))
                .build();

        // Sending the request
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle the response

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getRequestTokenfromRefreshToken(String refreshToken, String clientid) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormDataFromMap(Map.of(
                        "refresh_token", refreshToken,
                        "grant_type", "refresh_token",
                        "client_id", clientid
                )))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle the response

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


}
