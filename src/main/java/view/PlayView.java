package view;

import interface_adapter.guess.GuessController;
import interface_adapter.play_song.PlayController;
import interface_adapter.play_song.PlayState;
import interface_adapter.play_song.PlayViewModel;
import interface_adapter.skip_song.SkipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayView extends JPanel implements ActionListener {
    // views
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private PlayViewModel playViewModel;

    // buttons
    private final JButton skipButton;

    // textfields
    private JTextField guessInputField = new JTextField(15);

    // controllers
    private final SkipController skipController;
    private GuessController guessController;

    public PlayView(PlayViewModel playViewModel,
                    PlayController playController,
                    SkipController skipController,
                    GuessController guessController) {
        this.playViewModel = playViewModel;
        this.guessController = guessController;
        this.skipController = skipController;

        this.setLayout(new BorderLayout()); // Use BorderLayout for simplicity

        // Create a panel to hold the button and add some white space around it
        JPanel panel = new JPanel();
        LabelTextPanel guessInfo = new LabelTextPanel(new JLabel(PlayViewModel.ANSWER_LABEL), guessInputField);

        // Create the skip button and set its size and action listener
        skipButton = new JButton("Skip");
        skipButton.addActionListener(this);
        skipButton.setPreferredSize(new Dimension(200, 100)); // Set the preferred size of the button

        // Use GridBagLayout for the button panel to center the button
        panel.setLayout(new GridBagLayout());
        panel.add(skipButton); // Add the button to the button panel

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

        // Add the button panel to the center of the BorderLayout, which will provide white space automatically
        this.add(panel, BorderLayout.CENTER);
        this.add(guessInfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(skipButton)) {
            skipController.execute(); // Ensure this method is implemented in the PlayController
        }
    }
}
