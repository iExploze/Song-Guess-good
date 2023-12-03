package entities;

import java.io.IOException;

public interface Player {
    // potentially a setName() method later on allowing users to change their name after making an account
    // might add login data (for our program) or spotify login data

    String getName();

    void setPoints(float points);

    String getPoints();

    Playlist getPlayList();

    void setPlaylist() throws IOException;
}
