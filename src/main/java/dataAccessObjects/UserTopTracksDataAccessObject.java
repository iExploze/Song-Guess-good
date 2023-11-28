package dataAccessObjects;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import entities.Song;
import entities.Users.User;

import javax.ws.rs.core.Link;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserTopTracksDataAccessObject implements userTrackandPlaylistDataAccessObject {

    private User user;
    public UserTopTracksDataAccessObject(User user) {
        this.user = user;
    }
    public class NeedRefreshException extends Exception {
        public NeedRefreshException(String message) {
            super(message);
        }
    }

    //@Override
    public String getTopTracks() throws IOException, NeedRefreshException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/me/top/tracks?limit=50"))
                .header("Authorization", "Bearer " + this.user.getAccessToken())
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle the response
            System.out.println("Response Code: " + response.statusCode());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public List<HashMap<String, String>> topTracksResponseToNamePreviewUrl(String s) {
        Gson gson = new Gson();
        HashMap allData = gson.fromJson(s, HashMap.class);

        List<HashMap<String, String>> res = new ArrayList<>();
        for (Object SongData : (ArrayList) allData.get("items")) {
            LinkedTreeMap SongData1 = (LinkedTreeMap) SongData;
            HashMap<String, String> resultData = new HashMap();
            resultData.put("name", (String) SongData1.get("name"));
            if (SongData1.get("preview_url") == null) {
                continue;
            }
            resultData.put("preview_url", (String) SongData1.get("preview_url"));
            res.add(resultData);


        }
        return res;
    }
}
