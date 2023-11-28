package dataAccessObjects;

import java.io.IOException;

public interface userTrackandPlaylistDataAccessObject {

    /*given a user, should return their top tracks in JSON format*/

    String getTopTracks() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException;
}
