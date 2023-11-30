package entities;

import java.util.ArrayList;

public class PlaylistQuiz implements Quiz{
    private final Playlist SongList;
    private final Player player;
    private Song curr; // the song currently playing
    private int index;
    private int timeLeft;
    private int points;
    public PlaylistQuiz(Player player)
    {
        this.index = 0;
        this.SongList = player.getPlayList();
        this.curr = this.SongList.getSong(this.index);
        this.player = player;
        this.timeLeft = 120;
        this.points = 188;
    }
    @Override
    public ArrayList<Player> players() {
        ArrayList<Player> temp = new ArrayList<>();
        temp.add(this.player);
        return temp ;
    }

    @Override
    public Song currentPlaying() {
        return this.curr;
    }

    @Override
    public void goNext() {
        this.index++;
        this.curr = SongList.getSong(this.index);
    }

    @Override
    public double getTimeLeft() {
        return timeLeft;
    }

    @Override
    public int getRemaining() {
        return 0;
    }

    @Override
    public boolean isSong(String name) {
        return false;
    }

    public void addPoints(){
        this.points = this.points + 1;
    }

    public void addPoints(int amount){
        this.points = this.points + amount;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    public void decreaseTime() {
        this.timeLeft = this.timeLeft - 1;
    }

    public void decreaseTime(int amount) {
        this.timeLeft = this.timeLeft - amount;
    }
}
