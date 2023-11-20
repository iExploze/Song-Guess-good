package usecase.play_song;

import entities.Player;

import java.util.Collection;

public interface PlayUserDataAccessInterface {
    String getsong();
    boolean existSongPreview();
    Collection<String> existing_Previews();
    void removesong();
    void addsong();
}
