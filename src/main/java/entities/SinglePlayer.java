package entities;

import dataAccessObjects.spotifyAccessObjects.UserTopTracksDataAccessObject;
import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SinglePlayer implements Player {
    private float points;
    private Playlist playlist;
    private User user;

    public SinglePlayer(User user, List<HashMap<String, String>> tracks)
    {
        this.user = user;
        this.playlist = new SpotifyPlaylist(tracks);
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
