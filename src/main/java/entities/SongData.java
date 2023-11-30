package entities;

import entities.Song;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;

public class SongData implements Song {
    String name;
    String url;

    public SongData(String name)
    {
        this.name = name;
        this.url = getURL();
    }
    @Override
    public String getArtistName() {
        return null;
    }

    @Override
    public String getSongName() {
        int length = 5;
        Random random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    @Override
    public String getSongGenre() {
        return null;
    }

    @Override
    public String getNumStreams() {
        return null;
    }

    @Override
    public String getURL() {
        return null;
    }

    /**
     * Plays an MP3 audio file from a given URL
     *
     * This method accesses the audio stream from a given URL, converts it to a usable format,
     * and plays it through the default audio format
     *
     * Referenced from https://docs.oracle.com/javase/tutorial/sound/converters.html
     * and https://docs.oracle.com/javase/8/docs/technotes/guides/sound/programmer_guide/chapter4.html
     *
     *
     */
    @Override
    public void playSong() {
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
    }
}
