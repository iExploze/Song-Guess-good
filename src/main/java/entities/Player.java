package main.java.entities;

import java.time.LocalDateTime;

public interface Player {
    // potentially a setName() method later on allowing users to change their name after making an account
    // might add login data (for our program) or spotify login data
    String getName();

    String getPassword();

    String setPoints();

    String getPoints();

    boolean spotifyAuthenticated();

    LocalDateTime getCreationTime();
}
