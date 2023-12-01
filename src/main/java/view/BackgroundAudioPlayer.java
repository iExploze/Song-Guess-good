package view;

import entities.Song;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class BackgroundAudioPlayer extends SwingWorker<Void, Void> {
    private volatile boolean isPlaying = false;
    private Song song;
    private SourceDataLine line;
    public BackgroundAudioPlayer() {    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            if (isPlaying) {
                playSong(song);
            } else {
                stopPlayback();
            }
        }
        return null;
    }
    private void playSong(Song song) {
        AudioInputStream converted = null;
        try {
            // get the audio input stream from the song URL
            // checks if it's a supported format
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new URL(song.getURL()));

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
            line = (SourceDataLine) AudioSystem.getLine(info);

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
                while (isPlaying) {
                    bytesRead = converted.read(byteData, 0, byteData.length/30);
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
        isPlaying = false;
    }

    private void stopPlayback() {
        if (line != null && line.isOpen()) {
            line.drain();
            line.stop();
            line.close();
        }
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
    public void stopPlaybackManually() {
        stopPlayback();
        cancel(true);
    }
    public boolean isPlaying() {
        return isPlaying;
    }
    public void cleanup() {
        stopPlayback();
        if (line != null) {
            line.close();
        }
    }
}
