package view;

import app.TextFieldSuggestion;
import interface_adapter.guess.GuessController;
import interface_adapter.play_song.PlayController;
import interface_adapter.PlayViewModel;
import interface_adapter.PlayState;
import interface_adapter.score.ScoreController;
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
    private final JButton play;
    private final JButton next;

    private TextFieldSuggestion guessInputField;

    private final SkipController skipController;
    private final PlayController playController;
    private final ScoreController scoreController;
    private final TimerController timerController;
    private GuessController guessController;
    private JLabel scoreLabel;
    private final JLabel timeLabel;
    private JProgressBar timerProgress;
    private int score = 0;
    private  int timeLeft = 30; // time remaining for progress bar
    private int totalTime = 120; // total time left to play
    Timer timer;

    public PlayView(PlayController playController, PlayViewModel playViewModel,
                    GuessController guessController, SkipController skipController,
                    ScoreController scoreController, TimerController timerController) {
        this.playViewModel = playViewModel;
        this.playController = playController;
        this.skipController = skipController;
        this.guessController = guessController;
        this.scoreController = scoreController;
        this.timerController = timerController;
        //this.setLayout(new BorderLayout());
        playViewModel.addPropertyChangeListener(this);

        // Set the background color for the main panel
        //this.setBackground(new Color(64, 64, 64)); // Dark grey

        // Get the user's guess
        LabelTextPanel guessInfo = new LabelTextPanel(
                new JLabel(PlayViewModel.ANSWER_LABEL), guessInputField);

        // Initialize score
        this.score = 0;
        this.scoreLabel = new JLabel(
                ("Score: " + this.score))
        this.scoreLabel = new JLabel("Score: " + this.score);
        this.scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.timeLabel.setForeground(Color.WHITE);

        // Initialize the timer bar
        timerProgress = new JProgressBar(0, timeLeft);

        // Initialize Timer
        this.timeLeft = 0;
        this.timeLabel = new JLabel(
                ("Time: " + this.timeLeft));
        this.timeLabel = new JLabel("Time: " + this.totalTime);
        this.timeLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.timeLabel.setForeground(Color.WHITE); // White font for visibility

        // Score Panel - Positioned at the top right
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(this.scoreLabel, BorderLayout.EAST);
        scorePanel.add(this.timeLabel, BorderLayout.WEST);
        scorePanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add buttons for play and next
        JPanel buttons = new JPanel();
        play = new JButton(PlayViewModel.PLAY_BUTTON_LABEL);
        buttons.add(play);
        next = new JButton(PlayViewModel.NEXT_BUTTON_LABEL);
        buttons.add(next);

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
                        if (e.getKeyCode() == KeyEvent.VK_ENTER){
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

        play.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(play)) {
                            playController.execute();
                        }
                    }
                }
        );


        next.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(next)) {
                            skipController.execute();
                        }
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
                        resetTimer();
                        // go to next song
                    }
                }
        );

        // Add components to layout
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(guessInfo);
        this.add(buttons);
        this.add(timerProgress, BorderLayout.SOUTH);
        resetTimer();

        playViewModel.addPropertyChangeListener(this);
    }

    public void updateSuggestion(List<String> names) {
        for (String item : names) {
            guessInputField.addItemSuggestion(item);
        }
    }

    private void updateScore() {
        this.scoreLabel.setText("Score: " + this.playViewModel.getScore());
    }

    private void updateTime() {
        this.scoreLabel.setText("Time Left: " + this.playViewModel.getTime());
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


    @Override
    public void actionPerformed(ActionEvent evt) {


    }
}
