package entities;

import dataAccessObjects.spotifyAccessObjects.UserTopTracksDataAccessObject;
import entities.Users.User;

import java.io.IOException;

public class SinglePlayer implements Player {
    private float points;
    private Playlist playlist;
    private User user;

    public SinglePlayer(User user)
    {
        this.user = user;
        this.playlist = new SpotifyPlaylist();
        this.points = 0;
    }
    @Override
    public String getName() {
        return user.getUsername();
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
    public Playlist getPlayList() {
        return this.playlist;
    }
}
