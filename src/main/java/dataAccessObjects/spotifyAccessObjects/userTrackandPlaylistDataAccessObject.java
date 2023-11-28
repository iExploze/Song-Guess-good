package dataAccessObjects.spotifyAccessObjects;

import dataAccessObjects.spotifyAccessObjects.UserTopTracksDataAccessObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface userTrackandPlaylistDataAccessObject {

    /*given a user, should return their top tracks in JSON format*/

    String getTopTracks() throws IOException;

    List<HashMap<String, String>> topTracksResponseToNamePreviewUrl(String topTracks);
}
