package entities;

import java.time.LocalDateTime;

public interface Player {
    // potentially a setName() method later on allowing users to change their name after making an account
    // might add login data (for our program) or spotify login data

    String getName();

    String getPassword();

    void setPoints(float points);

    String getPoints();

    boolean spotifyAuthenticated();

    LocalDateTime getCreationTime();

    Playlist getPlayList();
}
