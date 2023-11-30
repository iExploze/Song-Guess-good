package view;

import interface_adapter.guess.GuessController;
import interface_adapter.play_song.PlayController;
import interface_adapter.PlayViewModel;
import interface_adapter.score.ScoreController;
import interface_adapter.play_song.PlayState;
import interface_adapter.play_song.PlayViewModel;
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

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;

    // buttons
    private final JButton skipButton;
    private final JButton pauseButton;
    private final JButton startButton;

    private JTextField guessInputField = new JTextField(15);

    private final SkipController skipController;
    private final ScoreController scoreController;
    private final TimerController timerController;
    private GuessController guessController;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private int score;
    private int timeLeft;

    public PlayView(PlayController playController, SkipController skipController, ScoreController scoreController, PlayViewModel playViewModel, TimerController timerController, GuessController guessController) {
        this.playViewModel = playViewModel;
        this.skipController = skipController;
        this.guessController = guessController;
        this.scoreController = scoreController;
        this.timerController = timerController;
        this.setLayout(new BorderLayout());

        // Set the background color for the main panel
        this.setBackground(new Color(64, 64, 64)); // Dark grey

        // Get the user's guess
        LabelTextPanel guessInfo = new LabelTextPanel(new JLabel(PlayViewModel.ANSWER_LABEL), guessInputField);

        // Initialize score
        this.score = 0;
        this.scoreLabel = new JLabel("Score: " + this.score);
        this.scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.scoreLabel.setForeground(Color.WHITE); // White font for visibility

        // Initialize Timer
        this.timeLeft = 0;
        this.timeLabel = new JLabel("Time: " + this.timeLeft);
        this.timeLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.timeLabel.setForeground(Color.WHITE); // White font for visibility

        // Create skip button
        this.skipButton = new JButton("Skip");
        this.skipButton.addActionListener(this);
        this.skipButton.setPreferredSize(new Dimension(200, 100));
        this.skipButton.setBackground(new Color(96, 96, 96)); // Slightly lighter grey for the button
        this.skipButton.setForeground(Color.BLACK); // White text for visibility

        // Create pause button
        this.pauseButton = new JButton("Pause");
        this.pauseButton.addActionListener(this);
        this.pauseButton.setPreferredSize(new Dimension(200, 100));
        this.pauseButton.setBackground(new Color(96, 96, 96)); // Slightly lighter grey for the button
        this.pauseButton.setForeground(Color.BLACK); // White text for visibility

        // Create reset button
        this.startButton = new JButton("Start Timer");
        this.startButton.addActionListener(this);
        this.startButton.setPreferredSize(new Dimension(200, 100));
        this.startButton.setBackground(new Color(96, 96, 96)); // Slightly lighter grey for the button
        this.startButton.setForeground(Color.BLACK); // White text for visibility

        // Panel for skip button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        buttonPanel.add(this.skipButton);
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.pauseButton);

        // Score Panel - Positioned at the top right
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(this.scoreLabel, BorderLayout.EAST);
        scorePanel.add(this.timeLabel, BorderLayout.WEST);
        scorePanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        guessInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlayState currentState = playViewModel.getState();
                        String input = guessInputField.getText();
                        currentState.setGuess(input);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER){
                            guessController.execute(guessInputField.getText());
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        // Add components to layout
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(guessInfo);

        playViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.skipButton)) {
            this.scoreController.getScore();
            this.skipController.execute(); // Ensure this method is implemented in the PlayController
        }

        if (e.getSource().equals(this.pauseButton)) {
            this.timerController.updateTimerState();
        }

        if (e.getSource().equals(this.startButton)) {
            this.timerController.setTimer(120);
            this.timerController.startTimer();
        }
    }

    private void updateScore() {
        this.scoreLabel.setText("Score: " + this.playViewModel.getScore());
    }

    private void updateTime() {
        this.scoreLabel.setText("Time Left: " + this.playViewModel.getTime());
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

    }
}
