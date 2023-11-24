package entities;

import dataAccessObjects.userTopTracksDataAccessObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public interface Player {
    // potentially a setName() method later on allowing users to change their name after making an account
    // might add login data (for our program) or spotify login data

    String getName();

    void setPoints(float points);

    String getPoints();

    HashMap getTopTracks() throws IOException, userTopTracksDataAccessObject.NeedRefreshException;
    Playlist getPlayList();
}
