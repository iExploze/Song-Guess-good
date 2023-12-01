package view;

import app.TextFieldSuggestion;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.play_song.PlayController;
import interface_adapter.PlayViewModel;
import interface_adapter.score.ScoreController;
import interface_adapter.PlayState;
import interface_adapter.skip_song.SkipController;
import interface_adapter.timer.TimerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;
    private PlayState playState;

    // buttons

    private TextFieldSuggestion guessInputField;
    private final ScoreController scoreController;
    private final TimerController timerController;

    private final PlayController playController;
    private GuessController guessController;

    private JButton startButton;
    private JLabel scoreLabel;
    private final JLabel timeLabel;
    private JProgressBar timerProgress;
    private int score = 0;
    private  int timeLeft = 30; // time remaining for progress bar
    private int totalTime = 120; // total time left to play
    Timer timer;

    public PlayView(PlayController playController,
                    ScoreController scoreController,
                    PlayViewModel playViewModel,
                    TimerController timerController,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.playController = playController;
        this.guessController = guessController;
        this.scoreController = scoreController;
        this.timerController = timerController;
        this.setLayout(new BorderLayout());
        this.guessInputField = new TextFieldSuggestion();
        this.guessInputField.setPreferredSize(new Dimension(200, 30));
        this.playState = new PlayState();
        // Set the background color for the main panel
        this.setBackground(new Color(64, 64, 64)); // Dark grey

        this.startButton = new JButton("Start");
        startButton.addActionListener(e -> startUp());
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        startButton.setPreferredSize(new Dimension(200, 30)); // Match the size of the guess input field

        // Get the user's guess panel
        LabelTextPanel guessInfo = new LabelTextPanel(new JLabel(PlayViewModel.ANSWER_LABEL), guessInputField);
        guessInfo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel

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
        scorePanel.add(this.startButton, BorderLayout.CENTER);
        scorePanel.add(this.timeLabel, BorderLayout.WEST);
        scorePanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        guessInputField.setRound(15);
        guessInputField.setBackground(new Color(255,255,255));
        guessInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlayState currentState = playViewModel.getState();
                        String input = guessInputField.getText();
                        //currentState.setGuess();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER){
                            timer.stop();
                            timerController.updateTimerState();
                            guessController.execute(guessInputField.getText());
                            scoreController.getScore();
                            timerController.updateTimerState();
                            playController.StopSong();
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

                    if (timeLeft == 0){
                        timer.stop();
                        JOptionPane.showMessageDialog(null,"Time's up!");
                        this.playController.StopSong();
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

    public void startUp()
    {
        this.timerController.setTimer(120);
        this.timerController.startTimer();
        this.playController.PlaySong();
    }

    public void updateSuggestion(List<String> names) {
        if(names == null)
            return;
        for (String item: names) {
            guessInputField.addItemSuggestion(item);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void updateScore() {
        this.scoreLabel.setText("Score: " + this.playViewModel.getScore());
    }

    private void updateTime() {
        this.timeLabel.setText("Time Left: " + this.playViewModel.getTime());
    }

    private void resetTimer(){
        timeLeft = 30;
        timerProgress.setValue(0);
        timer.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if ("score".equals(evt.getPropertyName())) {
            updateScore();
        }
        if ("time".equals(evt.getPropertyName())) {
            //System.out.println("L");
            updateTime();
        }
        if ("suggestion".equals(evt.getPropertyName())) {
            PlayState state = (PlayState) evt.getNewValue();
            updateSuggestion(state.getSuggestions());
        }

    }
}
