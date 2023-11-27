package view;

import interface_adapter.UAuthController;
import interface_adapter.UAuthViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class UAuthView extends JPanel implements ActionListener {

    public static final String viewName = "UAUTH_VIEW"; // Add a static constant for the view name

    private final UAuthController uAuthController;

    private String URL = "";

    private final JButton URLbutton;

    public UAuthView(UAuthController uAuthController, UAuthViewModel uAuthViewModel)
    {
        this.uAuthController = uAuthController;
        this.URL = uAuthViewModel.getUrl();

        URLbutton = new JButton("URL");
        URLbutton.addActionListener(this);
        URLbutton.setPreferredSize(new Dimension(200, 100)); // Set the preferred size of the button

        // Create a panel to hold the button and add some white space around it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the button panel to center the button
        buttonPanel.add(URLbutton); // Add the button to the button panel

        // Add the button panel to the center of the BorderLayout, which will provide white space automatically
        this.add(buttonPanel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(URLbutton)) {
            openWebPage(URL);
        }
    }

    private void openWebPage(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported. Cannot open the webpage.");
        }
    }
}
