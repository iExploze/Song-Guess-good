package dataAccessObjects.spotifyAccessObjects;

import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserTopTracksObject implements UserTopTracks{

    public UserTopTracksObject() {}
    @Override
    public List<HashMap<String, String>> getTopTracks(User user) throws IOException {
        userOAuthObject userOAuthObject = new userOAuthObject();
        userTrackandPlaylistDataAccessObject userTopTracksDataAccessObject = new UserTopTracksDataAccessObject(user);
        SpotifyAuthenticationObject spotifyAuthenticationObject = new SpotifyAuthenticationObject();
        RandomSecureS256Generator randomSecureS256Generator = new RandomSecureS256Generator();
        UserAuthenticationBuilder userAuthenticationBuilder = new UserAuthenticationBuilder(user, userOAuthObject, userTopTracksDataAccessObject,
                spotifyAuthenticationObject, randomSecureS256Generator);
        return userAuthenticationBuilder.getTopTracks();
    }
}
