package dataAccessObjects;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SongStorageDataAccessObject {
    private HashMap<String, String> songPreviewLinks; // Key: Song title, Value: Preview link

    public SongStorageDataAccessObject() {
        this.songPreviewLinks = new HashMap<>();
    }

    // Check if there is a song preview link for all the songs in player's playlist
    public boolean existSong() {
        return songPreviewLinks.isEmpty();
    }

    // Get a song preview link
    public String getSong(String title) {
        return songPreviewLinks.get(title);
    }

    // actually play the song using the link to a mp3 file
    public void playSong(String url) {
    };

    // Add a song preview link
    public void addSong(String title, String previewLink) {
        songPreviewLinks.put(title, previewLink);
    }

    // Remove a song preview link
    public void removeSong(String title) {
        songPreviewLinks.remove(title);
    }
}
