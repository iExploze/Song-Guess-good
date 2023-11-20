package view;

import interface_adapter.guess.GuessController;
import interface_adapter.play_song.PlayState;
import interface_adapter.play_song.PlayViewModel;
import interface_adapter.play_song.PlayController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "PLAY";
    private final JTextField answerInputField = new JTextField(15);
    private final PlayViewModel playViewModel;
    private final PlayController playController;

    private final GuessController guessController;
    private final JButton play;
    //private final JButton stop;
    private final JButton guess;

    public PlayView(PlayViewModel playViewModel,
                    PlayController playController,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.playController = playController;
        this.guessController = guessController;

        playViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(PlayViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel answerInfo = new LabelTextPanel(
                new JLabel(PlayViewModel.ANSWER_LABEL), answerInputField);

        JPanel buttons = new JPanel();
        play = new JButton(PlayViewModel.PLAY_BUTTON_LABEL);
        buttons.add(play);
        guess = new JButton(PlayViewModel.GUESS_BUTTON_LABEL);
        buttons.add(guess);

        guess.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(guess)) {
                            guessController.execute(playViewModel.getState().getGuess());
                        }
                    }
                }
        );

        answerInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlayState currentState = playViewModel.getState();
                        currentState.setGuess(answerInputField.getText() + e.getKeyChar());
                        playViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(answerInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

