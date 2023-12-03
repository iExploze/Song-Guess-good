package entities;

import java.util.List;

public interface Quiz {

    void setQuiz(Playlist songList);

    Song currentPlaying();
    public List<String> getSuggestions();
    void goNext();

    int getPoints();

    public void addPoints();


    void setSuggestions(List<String> suggestions);
}
