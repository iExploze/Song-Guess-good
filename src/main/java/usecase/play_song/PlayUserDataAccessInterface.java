package usecase.play_song;

import entities.Player;

import java.util.Collection;

public interface PlayUserDataAccessInterface {
    boolean existSong(String title);
    String getSong(String title);
    void playSong(String url);
}
