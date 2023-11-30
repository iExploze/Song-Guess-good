package view;

import interface_adapter.play_song.PlayController;
import interface_adapter.PlayViewModel;
import interface_adapter.score.ScoreController;
import interface_adapter.skip_song.SkipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "PLAY_VIEW"; // Add a static constant for the view name
    private final JButton skipButton;
    private final SkipController skipController;
    private final ScoreController scoreController;
    private JLabel scoreLabel;
    private int score;
    private PlayViewModel playViewModel;

    public PlayView(PlayController playController, SkipController skipController, ScoreController scoreController, PlayViewModel playViewModel) {
        this.playViewModel = playViewModel;
        this.skipController = skipController;
        this.scoreController = scoreController;
        this.setLayout(new BorderLayout());

        // Set the background color for the main panel
        this.setBackground(new Color(64, 64, 64)); // Dark grey

        // Initialize score
        score = 0;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        scoreLabel.setForeground(Color.WHITE); // White font for visibility

        // Create skip button
        skipButton = new JButton("Skip");
        skipButton.addActionListener(this);
        skipButton.setPreferredSize(new Dimension(200, 100));
        skipButton.setBackground(new Color(96, 96, 96)); // Slightly lighter grey for the button
        skipButton.setForeground(Color.BLACK); // White text for visibility

        // Panel for skip button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        buttonPanel.add(skipButton);

        // Score Panel - Positioned at the top right
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.EAST);
        scorePanel.setBackground(new Color(64, 64, 64)); // Dark grey background
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to layout
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.NORTH);

        playViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(skipButton)) {
            scoreController.getScore();
            skipController.execute(); // Ensure this method is implemented in the PlayController
        }
    }

    private void updateScore(int newScore) {
        scoreLabel.setText("Score: " + newScore);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("score".equals(evt.getPropertyName())) {
            updateScore((Integer) evt.getNewValue());
        }
    }
}
