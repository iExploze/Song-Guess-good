package entities;



import entities.Users.User;

import java.time.LocalDateTime;

public interface PlayerFactory {

    Player create(User user);
}
