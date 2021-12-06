package User;

import javax.swing.*;
import java.awt.*;


public class UFindIntro {
    private JButton signUpButton;
    private JButton signInButton;
    private JPanel panel1;

    /**
     * Display the Intro section of the user interface
     *
     *
     */
    public UFindIntro(){
        JFrame frame = new JFrame("Ufind");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        signInButton.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            new UserLogIn();
        });

        signUpButton.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            new SignUpWindow();});
    }
}
