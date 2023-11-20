package entities;

import entities.Player;

import java.util.ArrayList;

public class SinglePlayer implements Player {


    private final SpotifyPlaylist playlist;
    private final String name;
    private float points;

    public SinglePlayer(String name, SpotifyPlaylist playlist)
    {
        this.name = name;
        this.playlist = playlist;
    }
    @Override
    public String getName() {
        return name;
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
    public Playlist getPlayList() {
        return this.playlist;
    }
}
