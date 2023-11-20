package entities;

import java.util.ArrayList;

public class PlaylistQuiz implements Quiz{
    private Playlist SongList;
    private final Player player;
    private Song curr; // the song currently playing
    private int index;
    public PlaylistQuiz(Player player)
    {
        this.index = 0;
        this.curr = SongList.getSong(this.index);
        this.player = player;
        SongList = player.getPlayList();
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
        return 0;
    }

    @Override
    public int getRemaining() {
        return 0;
    }

    @Override
    public boolean isSong(String name) {
        return false;
    }

    @Override
    public int getPoints() {
        return 0;
    }
}
