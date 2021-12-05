package User;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SignUpWindow {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton signUpButton;
    private JPanel panel1;
    private final JFrame frame;

    public SignUpWindow() {
        frame = new JFrame("UFind");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setResizable(false);

        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signUpButton.addActionListener(e -> {
            String username = textField1.getText();
            String password1 = new String(passwordField1.getPassword());
            String password2 = new String(passwordField2.getPassword());
            if (!Objects.equals(password1, password2)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match");
            }
            else if (Objects.equals(password1, "") || Objects.equals(username, "")){
                JOptionPane.showMessageDialog(frame, "Account fields can not be empty");
            }
            else{
                MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
                        + File.separator  + "Journal Entries");
                try {
                    Create_User.make_user(username, password1, dir.getPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println(dir.getPath());
                JOptionPane.showMessageDialog(frame, "Account Created!");
                new UFindIntro();

            }
});}}
