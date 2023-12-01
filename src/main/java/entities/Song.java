package entities;

public interface Song {
    String getArtistName();

    String getSongName();

    //may not need song genre
    String getSongGenre();

    String getNumStreams();

    String getURL();

    String playSong();
    // gets the mp3 file and plays the song
}
