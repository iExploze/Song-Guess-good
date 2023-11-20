package entities;

import entities.Player;

import java.time.LocalDateTime;

public class SinglePlayer implements Player {
    private final String name;
    private float points;
    private Playlist playlist;

    public SinglePlayer(String name)
    {
        this.name = name;
        this.playlist = new SpotifyPlaylist();
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPoints(float points) {
        this.points = points;
    }

    @Override
    public String getPoints() {
        return (String.valueOf(this.points));
    }

    @Override
    public boolean spotifyAuthenticated() {
        return false;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return null;
    }

    @Override
    public Playlist getPlayList() {
        return this.playlist;
    }
}
