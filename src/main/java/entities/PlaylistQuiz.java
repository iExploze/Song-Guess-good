package entities;

import java.util.List;

public class PlaylistQuiz implements Quiz{
    private Playlist SongList;
    private final Player player;
    private Song curr; // the song currently playing
    private int index;
    private int points;
    private List<String> suggestions;
    private int timeLeft;
    public PlaylistQuiz(Player player)
    {
        this.index = 0;
        this.curr = null;
        this.player = player;
        this.timeLeft = 0;
        this.points = 0;
        this.suggestions = null;
    }

    @Override
    public void setQuiz(Playlist songList) {
        this.SongList = songList;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }



    @Override
    public Song currentPlaying() {
        if(this.curr == null) {
            this.curr = this.SongList.getSong(this.index);
        }
        return this.curr;
    }

    @Override
    public void goNext() {

        if(this.curr == null) {
            this.curr = this.SongList.getSong(this.index);
        }
        else {
            this.index++;
            this.curr = this.SongList.getSong(this.index);
        }
    }

    public void addPoints(){
        this.points = this.points + 1;
    }

    @Override
    public void decreaseTime() {
        this.timeLeft = this.timeLeft - 1;
    }

    @Override
    public int getTimeLeft() {
        return this.timeLeft;
    }

    @Override
    public void setTime(int time) {
        this.timeLeft = time;
    }

    @Override
    public int getPoints() {
        return this.points;
    }


}
