package entities;

import dataAccessObjects.UserAccessToken;
import dataAccessObjects.userTopTracksDataAccessObject;
import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;

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


    public HashMap getTopTracks() throws IOException, userTopTracksDataAccessObject.NeedRefreshException {
        if (this.user.checkExpired()) {
            UserAccessToken u = new UserAccessToken();
            u.getUpdatedAccessToken(this.user);
        }
        try {
            userTopTracksDataAccessObject utd = new userTopTracksDataAccessObject(this.user);
            return utd.getTopTracks();
        } catch (userTopTracksDataAccessObject.NeedRefreshException e) {
            UserAccessToken u = new UserAccessToken();
            u.getUpdatedAccessToken(this.user);
            userTopTracksDataAccessObject utd = new userTopTracksDataAccessObject(this.user);
            return utd.getTopTracks();
        }


    }

    @Override
    public Playlist getPlayList() {
        return this.playlist;
    }
}
