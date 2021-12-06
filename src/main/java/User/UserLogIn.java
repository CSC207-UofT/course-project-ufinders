package User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class UserLogIn extends JFrame {
    private JTextField usernameTextField;
    private JPanel panel1;
    private JPasswordField passwordPasswordField;
    private JButton signInButton;
    private JButton returnButton;
    private final JFrame frame;

    /**
     * Display the login section of the UI
     *
     *
     */
    public UserLogIn(){
        frame = new JFrame("Ufind");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        signInButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = new String(passwordPasswordField.getPassword());
            String hypothetical_path = User_Controller.retrieveAccount(username);
            if (!User_Controller.checkAccount(username) ||
                    (!Objects.equals(password, User_Controller.readPassword(hypothetical_path)))) {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
            else{
              // Add text box here to select program choice
                frame.setVisible(false);
                frame.dispose();
                new FeatureSelection(username);

            }
        });
        returnButton.addActionListener(e -> {
                frame.setVisible(false);
                frame.dispose();
                new UFindIntro();
        });
    }
}
