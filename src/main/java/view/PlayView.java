package view;

import interface_adapter.play_song.PlayController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayView extends JPanel implements ActionListener {
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private final JButton skipButton;
    private final PlayController playController;

    public PlayView(PlayController playController) {
        this.playController = playController;
        this.setLayout(new BorderLayout()); // Use BorderLayout for simplicity

        // Create the skip button and set its size and action listener
        skipButton = new JButton("Skip");
        skipButton.addActionListener(this);
        skipButton.setPreferredSize(new Dimension(200, 100)); // Set the preferred size of the button

        // Create a panel to hold the button and add some white space around it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the button panel to center the button
        buttonPanel.add(skipButton); // Add the button to the button panel

        // Add the button panel to the center of the BorderLayout, which will provide white space automatically
        this.add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(skipButton)) {
            playController.execute(); // Ensure this method is implemented in the PlayController
        }
    }
}
