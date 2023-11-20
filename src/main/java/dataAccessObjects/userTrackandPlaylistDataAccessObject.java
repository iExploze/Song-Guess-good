package dataAccessObjects;

import com.google.gson.JsonObject;
import entities.Users.User;

public interface userTrackandPlaylistDataAccessObject {

    /*given a user, should return their top tracks in JSON format*/
    JsonObject getTopTracks(User user);
}
