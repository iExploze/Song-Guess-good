package main.java.entities;

import java.util.ArrayList;

public interface Quiz {
    ArrayList<Player> players();

    Song currentPlaying();

    Song goNext();

    double getTimeLeft();

    int getRemaining();

    boolean isSong(String name);

    int getPoints();
}
