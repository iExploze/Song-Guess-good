package entities;

import entities.Player;

import java.time.LocalDateTime;

class SinglePlayer implements Player {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String setPoints() {
        return null;
    }

    @Override
    public String getPoints() {
        return null;
    }

    @Override
    public boolean spotifyAuthenticated() {
        return false;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return null;
    }
}
