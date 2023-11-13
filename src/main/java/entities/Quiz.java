package entities;

import java.util.ArrayList;

public interface Quiz {
    ArrayList<Player> players();

    Song currentPlaying();

    void goNext();

    double getTimeLeft();

    int getRemaining();

    boolean isSong(String name);

    int getPoints();
}
