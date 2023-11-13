package entities;

public interface Song {
    String getArtistName();

    String getSongName();

    String getSongGenre();

    String getNumStreams();

    void playSong();
    // gets the mp3 file and plays the song
}
