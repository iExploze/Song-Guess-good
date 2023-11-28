package entities;



import java.time.LocalDateTime;

public interface PlayerFactory {

    Player create(User user);
}
