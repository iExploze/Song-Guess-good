package entities;

import java.util.ArrayList;

public class PlaylistQuiz implements Quiz{
    private Playlist SongList;
    public PlaylistQuiz(Player player)
    {
        SongList = player.getPlayList();
    }
    @Override
    public ArrayList<Player> players() {
        return null;
    }

    @Override
    public Song currentPlaying() {
        return null;
    }

    @Override
    public void goNext() {

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
