package entities;


import entities.Users.User;

public interface PlayerFactory {

    Player create(User user);
}
