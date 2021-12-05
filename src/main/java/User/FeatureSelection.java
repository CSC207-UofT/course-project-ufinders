package User;


import Events.EventWindow;
import Journal.JournalUI;
import Marketplace.MarketplaceUI;

import javax.swing.*;
import java.awt.*;

public class FeatureSelection {
    private JButton eventsButton;
    private JButton journalButton;
    private JButton marketplaceButton;
    private JPanel panel1;

    public FeatureSelection(String username){
        JFrame frame = new JFrame("Ufind");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        eventsButton.addActionListener(e -> {
            String[] arguments = new String[]{username};
            EventWindow.main(arguments);
        });

        journalButton.addActionListener(e -> {
            String user_file = User_Controller.retrieve_account(username);
            String[] dir_path = {User_Controller.read_directory(user_file)};
            JournalUI.main(dir_path);
        });

        marketplaceButton.addActionListener(e -> MarketplaceUI.intro());
    }
}

