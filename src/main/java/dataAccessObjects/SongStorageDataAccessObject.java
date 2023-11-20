package dataAccessObjects;


import java.util.Collection;
import java.util.HashMap;

public class SongStorageDataAccessObject {
    private HashMap<String, String> songPreviewLinks; // Key: Song title, Value: Preview link

    public SongStorageDataAccessObject() {
        this.songPreviewLinks = new HashMap<>();
    }

    // Add a song preview link
    public void addSong(String title, String previewLink) {
        songPreviewLinks.put(title, previewLink);
    }

    // Check if there is a song preview link for all the songs in player's playlist
    public boolean existSongPreview() {
        return songPreviewLinks.isEmpty();
    }
    public Collection<String> existing_Previews() {
        return songPreviewLinks.values();
    }
    // Get a song preview link
    public String getSong(String title) {
        return songPreviewLinks.get(title);
    }

    // Remove a song preview link
    public void removeSong(String title) {
        songPreviewLinks.remove(title);
    }

    /*public static void main(String[] args) {
        SongStorage songStorage = new SongStorage();

        // Adding songs
        songStorage.addSong("Song1", "https://example.com/song1-preview");
        songStorage.addSong("Song2", "https://example.com/song2-preview");

        // Getting and printing song preview links
        System.out.println("Song1 Preview Link: " + songStorage.getSong("Song1"));
        System.out.println("Song2 Preview Link: " + songStorage.getSong("Song2"));

        // Removing a song
        songStorage.removeSong("Song1");

        // Trying to get the removed song
        System.out.println("Song1 Preview Link after removal: " + songStorage.getSong("Song1"));
    }*/
}
