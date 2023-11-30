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
        AudioInputStream converted = null;
        try {
            // get the audio input stream from the song URL
            // checks if it's a supported format
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new URL(url));

            // get the audio stream format
            AudioFormat sourceFormat = audioInput.getFormat();

            // converts the original audio input format so that it can be used
            // allows us to specify the encoding, sampleRate, and other data
            AudioFormat convertFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    sourceFormat.getSampleRate(), 16, sourceFormat.getChannels(),
                    sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(),
                    false);

            // gets the desired audio format after conversion
            converted = AudioSystem.getAudioInputStream(convertFormat, audioInput);

            // construct a dataline specifying the desired audio line related to the given audio format
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, convertFormat);

            // fetches the audio line that matches the above requirements
            // allows us to manipulate the audio (play, stop, etc)
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            if (line != null) {
                // opens the audio line using the desired audio format
                // start the line which allows it to start processing data
                line.open(convertFormat);
                line.start();

                // creates an array of bytes to read audio data
                byte[] byteData = new byte[4096];
                int bytesRead;

                // loop that reads from the desired audio format and writes it to the line
                // which plays the inputted song
                // stops when there is no more data to be read
                while (true) {
                    bytesRead = converted.read(byteData, 0, byteData.length);
                    if (bytesRead == -1) {
                        break;
                    }
                    line.write(byteData, 0, bytesRead);
                }

                // waits for the line to finish playing
                // stops the line from processing any further info and closes it
                // closes the converted audio source that was being read from
                line.drain();
                line.stop();
                line.close();
                converted.close();
            }

        }
        // handle exceptions
        catch (Exception e) {
            e.printStackTrace();
        }

        // if the converted source still exists
        // close it
        finally {
            if (converted != null) {
                try {
                    converted.close();
                } catch (IOException e) {
                }
            }
        }
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
