package entities;

import java.util.ArrayList;
import java.util.List;

public interface Quiz {

    void setQuiz(Playlist songList);
    ArrayList<Player> players();

    Song currentPlaying();
    public List<String> getSuggestions();
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

    void setSuggestions(List<String> suggestions);
}
