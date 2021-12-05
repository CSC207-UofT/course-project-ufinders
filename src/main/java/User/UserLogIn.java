package User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UserLogIn extends JFrame {
    private JTextField usernameTextField;
    private JPanel panel1;
    private JPasswordField passwordPasswordField;
    private JButton signInButton;
    private final JFrame frame;

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
            String hypothetical_path = User_Controller.retrieve_account(username);
            if (!User_Controller.check_account(username) ||
                    (!Objects.equals(password, User_Controller.read_password(hypothetical_path)))) {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
            else{
              // Add text box here to select program choice
                new FeatureSelection(username);
            }
        });
    }
}
