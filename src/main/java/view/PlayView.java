package view;

import interface_adapter.play_song.PlayViewModel;
import interface_adapter.play_song.PlayController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "PLAY";
    private final JTextField answerInputField = new JTextField(15);
    private final PlayViewModel playViewModel;
    private final PlayController playController;
    private final JButton play;
    private final JButton stop;

    public PlayView(PlayViewModel playViewModel, PlayController playController) {
        this.playViewModel = playViewModel;
        this.playController = playController;
        playViewModel.addPropertyChangeListener(this);

        JLabel titile = new JLabel(PlayViewModel.TITLE_LABEL);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

