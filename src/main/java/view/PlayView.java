package view;

import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.guess.GuessController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayView extends JPanel implements PropertyChangeListener {
    private int times = 0;
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;
    private PlayState playState;;

    private TextFieldSuggestion guessInputField;

    private GuessController guessController;
    private JLabel scoreLabel;
    private final JLabel timeLabel;
    private JProgressBar timerProgress;
    private int score = 0;
    private int timeLeft = 30; // time remaining for progress bar
    private int totalTime = 120; // total time left to play
    Timer timer;
    BackgroundAudioPlayer audioPlayer;

    public PlayView(PlayViewModel playViewModel,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.guessController = guessController;
        this.setLayout(new BorderLayout());
        this.guessInputField = new TextFieldSuggestion();
        this.guessInputField.setPreferredSize(new Dimension(200, 30));
        this.playState = new PlayState();
        // Set the background color for the main panel
        this.setBackground(new Color(64, 64, 64)); // Dark grey

        // Get the user's guess
        LabelTextPanel guessInfo = new LabelTextPanel(new JLabel(PlayViewModel.ANSWER_LABEL), guessInputField);

        // Initialize score
        this.scoreLabel = new JLabel("Score: " + this.score);
        this.scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.scoreLabel.setForeground(Color.WHITE); // White font for visibility

        // Initialize the timer bar
        timerProgress = new JProgressBar(0, timeLeft);

        // Initialize Timer
        this.timeLabel = new JLabel("Time: " + this.totalTime);
        this.timeLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.timeLabel.setForeground(Color.WHITE); // White font for visibility





        // Score Panel - Positioned at the top right
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(this.scoreLabel, BorderLayout.EAST);
        scorePanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        guessInputField.setRound(15);
        guessInputField.setBackground(new Color(255, 255, 255));
        guessInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlayState currentState = playViewModel.getState();
                        String input = guessInputField.getText();
                        //currentState.setGuess(input);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            timer.stop();
                            guessController.execute(guessInputField.getText());
                            resetTimer();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        timer = new Timer(1000,
                e -> {
                    timeLeft--;
                    timerProgress.setValue(30 - timeLeft);

                    if (timeLeft == 0) {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Time's up!");
                        guessController.execute(guessInputField.getText());
                        resetTimer();
                        // go to next song
                    }
                }
        );


        // Add components to layout

        this.add(scorePanel, BorderLayout.NORTH);
        this.add(guessInfo);
        this.add(timerProgress, BorderLayout.SOUTH);
        resetTimer();

        playViewModel.addPropertyChangeListener(this);
    }



    private void updateSuggestion(List<String> names) {
        for (String item : names) {
            guessInputField.addItemSuggestion(item);
        }
    }


    private void updateScore(int score) {
        this.playViewModel.setScore(score);
        this.scoreLabel.setText("Score: " + this.playViewModel.getScore());
    }


    private void updateSong(String song) { //something that plays the songaudioPlayer.setSong(song);
        if (times >= 50) {
            JOptionPane.showMessageDialog(null, "The Game is Over. You Score Was: " + this.score);
            System.exit(0);
        }
        if (audioPlayer != null) {
            audioPlayer.setPlaying(false);
            audioPlayer.cancel(true);
            audioPlayer.cleanup();
        }
        audioPlayer = new BackgroundAudioPlayer();

        // Add a property change listener to handle changes in the "playing" property
        audioPlayer.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    // Update the playing state of the audio player
                    if (evt.getNewValue() == "DONE") {
                        audioPlayer.setPlaying(false);
                    }
                    //audioPlayer.setPlaying((boolean) evt.getNewValue());
                    // Trigger SwingWorker to execute or cancel based on the playing state
                    if (audioPlayer.isPlaying()) {
                        audioPlayer.execute();
                    } else {
                        audioPlayer.stopPlaybackManually();
                    }
                }
            }
        });
        audioPlayer.setSong(song);
        audioPlayer.setPlaying(true);
        audioPlayer.execute();
        times++;

    }

    private void resetTimer() {
        timeLeft = 30;
        timerProgress.setValue(0);
        timer.start();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if ("state".equals(evt.getPropertyName())) {
            resetTimer();
            PlayState state = (PlayState) evt.getNewValue();
            updateScore(state.getScore());
            updateSong(state.getSong());
        }

        if ("suggestion".equals(evt.getPropertyName())) {
            PlayState state = (PlayState) evt.getNewValue();
            updateSuggestion(state.getSuggestions());
        }
    }
}
