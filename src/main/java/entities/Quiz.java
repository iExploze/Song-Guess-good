package entities;

import java.util.List;

public interface Quiz {

    void setQuiz(Playlist songList);

    Song currentPlaying();
    public List<String> getSuggestions();
    void goNext();

    int getPoints();

    public void addPoints();

    public void decreaseTime();

    public int getTimeLeft();

    public void setTime(int time);

    void setSuggestions(List<String> suggestions);
}
