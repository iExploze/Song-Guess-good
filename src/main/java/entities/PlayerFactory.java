package main.java.entities;

import entities.Player;
import entities.Users.User;

import java.time.LocalDateTime;

public interface PlayerFactory {

    Player create(User user);
}
