package dataAccessObjects.spotifyAccessObjects;

import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserTopTracks {
    List<HashMap<String, String>> getTopTracks(User user) throws IOException;
}
