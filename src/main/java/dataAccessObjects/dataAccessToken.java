package dataAccessObjects;

import entities.Users.User;

import java.io.IOException;

public interface dataAccessToken {

    public String getAccessToken(User user) throws IOException;

    public String getUpdatedAccessToken(User user) throws IOException;

}
