package entities;

import java.util.ArrayList;

public interface Quiz {

    void setQuiz(Playlist songList);
    ArrayList<Player> players();

    Song currentPlaying();

    void goNext();

    double getTimeLeft();

    int getRemaining();

    boolean isSong(String name);

    int getPoints();

    public void addPoints();

    public void addPoints(int amount);

    public void decreaseTime();

    public void decreaseTime(int amount);

    public void setTime(int time);
}
