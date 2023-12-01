package view;

import interface_adapter.guess.GuessController;
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

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;
    private final JButton play;
    private final JButton next;

    private JTextField guessInputField = new JTextField(15);

    //private final SkipController skipController;
    private final PlayController playController;
    //private final ScoreController scoreController;
    //private final TimerController timerController;
    private GuessController guessController;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private int score;
    private int timeLeft;


    public PlayView(PlayController playController, PlayViewModel playViewModel,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.playController = playController;
        //this.skipController = skipController;
        this.guessController = guessController;
        //this.scoreController = scoreController;
        //this.timerController = timerController;
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
                ("Score: " + this.score));
        this.scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        this.timeLabel.setForeground(Color.WHITE);

        // Initialize Timer
        this.timeLeft = 0;
        this.timeLabel = new JLabel(
                ("Time: " + this.timeLeft));
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
                            PlayView.this.guessController.execute(guessInputField.getText());
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

        /*
        next.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(next)) {
                            skipController.execute();
                        }
                    }
                }
        );
        */

        // Add components to layout
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(guessInfo);
        this.add(buttons);

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
        else if ("time".equals(evt.getPropertyName())) {
            //System.out.println("L");
            updateTime();
        }
        //else if (evt.getNewValue() instanceof PlayState) {
            //PlayState s = (PlayState) evt.getNewValue();
        //}
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
