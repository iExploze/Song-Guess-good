package entities;

import entities.Song;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;

public class SongData implements Song {
    private final String name;
    private final String url;
    private volatile boolean playing;
    private SourceDataLine line;
    private Thread playbackThread;

    public SongData(String name, String url)
    {
        this.name = name;
        this.url = url;
        this.playing = false;
    }
    @Override
    public String getArtistName() {
        return null;
    }

    @Override
    public String getSongName() {
        return this.name;
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
        return this.url;
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
        // If already playing, do nothing
        if (playing) {
            return;
        }
        playing = true;

        // Define the playback thread
        playbackThread = new Thread(() -> {
            AudioInputStream audioInput = null;
            AudioInputStream converted = null;
            try {
                // Get the audio input stream from the URL
                audioInput = AudioSystem.getAudioInputStream(new URL(url));
                AudioFormat baseFormat = audioInput.getFormat();
                AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(),
                        16,
                        baseFormat.getChannels(),
                        baseFormat.getChannels() * 2,
                        baseFormat.getSampleRate(),
                        false);
                // Get a decoded audio input stream
                converted = AudioSystem.getAudioInputStream(decodedFormat, audioInput);

                // Get a line that matches the decoded format
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
                line = (SourceDataLine) AudioSystem.getLine(info);
                if (line != null) {
                    line.open(decodedFormat);
                    byte[] buffer = new byte[4096];
                    line.start();
                    int bytesRead;
                    while (playing && (bytesRead = converted.read(buffer, 0, buffer.length)) != -1) {
                        line.write(buffer, 0, bytesRead);
                    }
                    line.drain();
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                if (line != null && line.isOpen()) {
                    line.stop();
                    line.close();
                }
                try {
                    if (audioInput != null) {
                        audioInput.close();
                    }
                    if (converted != null) {
                        converted.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Start the playback thread
        playbackThread.start();
    }


    @Override
    public void stopSong() {
        // Set the flag to false to stop the playback loop
        playing = false;

        // If the line is open, stop and close it
        if (line != null) {
            line.stop();
            line.close();
        }

        // Interrupt the playback thread and join
        if (playbackThread != null && playbackThread.isAlive()) {
            playbackThread.interrupt();
            try {
                playbackThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }
}
