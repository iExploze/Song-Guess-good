package view;

import app.TextFieldSuggestion;
import entities.Song;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.play_song.PlayController;
import interface_adapter.PlayViewModel;
import interface_adapter.score.ScoreController;
import interface_adapter.PlayState;
import interface_adapter.skip_song.SkipController;
import interface_adapter.timer.TimerController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener {
    private int times = 0;
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;
    private PlayState playState;
    private ExecutorService executorService;
    private TextFieldSuggestion guessInputField;
    private final ScoreController scoreController;
    private final TimerController timerController;
    private GuessController guessController;
    private JLabel scoreLabel;
    private final JLabel timeLabel;
    private JProgressBar timerProgress;
    private int score = 0;
    private int timeLeft = 30; // time remaining for progress bar
    private int totalTime = 120; // total time left to play
    Timer timer;
    BackgroundAudioPlayer audioPlayer;
    private boolean timerStarted;

    public PlayView(PlayController playController,
                    ScoreController scoreController,
                    PlayViewModel playViewModel,
                    TimerController timerController,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.guessController = guessController;
        this.scoreController = scoreController;
        this.timerController = timerController;
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
        scorePanel.add(this.timeLabel, BorderLayout.WEST);
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
                            updateTimerState();
                            timer.stop();
                            guessController.execute(guessInputField.getText());
                            resetTimer();
                            updateTimerState();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        this.timerStarted = false;

        timer = new Timer(1000,
                e -> {
                    timeLeft--;
                    timerProgress.setValue(30 - timeLeft);

                    if (timeLeft == 0) {
                        if(timerStarted)
                            updateTimerState();
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Time's up!");
                        guessController.execute(guessInputField.getText());
                        resetTimer();
                        // go to next song
                        if(timerStarted)
                            updateTimerState();
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



    public void updateSuggestion(List<String> names) {
        for (String item : names) {
            guessInputField.addItemSuggestion(item);
        }
    }

    private void updateTimerState(){
        this.timerController.updateTimerState();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void updateScore(int score) {
        this.playViewModel.setScore(score);
        this.scoreLabel.setText("Score: " + this.playViewModel.getScore());
    }


    private void updateSong(Song song) { //something that plays the songaudioPlayer.setSong(song);
        if (times >= 50) {
            JOptionPane.showMessageDialog(null, "The playlist is over, Your Score Was: " + this.score);
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

    private void startMainTimer(int seconds)
    {
        this.timerController.setTimer(seconds);
        this.timerController.startTimer();
        this.timerStarted = true;
    }

    private void updateMainTimer()
    {
        this.timeLabel.setText("Time: " + this.playViewModel.getTime());
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if ("state".equals(evt.getPropertyName())) {
            resetTimer();
            PlayState state = (PlayState) evt.getNewValue();
            updateScore(state.getScore());
            updateSong(state.getSong());
            if(!this.timerStarted)
                startMainTimer(120);
        }

        if ("time".equals(evt.getPropertyName()))
        {
            updateMainTimer();
            if (this.playViewModel.getTime() == 0)
            {
                JOptionPane.showMessageDialog(null, "The Game is Over. You Score Was: " + this.score);
                System.exit(0);
            }
        }

        if ("suggestion".equals(evt.getPropertyName())) {
            PlayState state = (PlayState) evt.getNewValue();
            updateSuggestion(state.getSuggestions());
        }

    }
}
