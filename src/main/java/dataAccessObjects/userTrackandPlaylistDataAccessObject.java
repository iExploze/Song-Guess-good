package dataAccessObjects;

import com.google.gson.JsonObject;
import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;

public interface userTrackandPlaylistDataAccessObject {

    /*given a user, should return their top tracks in JSON format*/
    HashMap getTopTracks(User user) throws IOException;

}
