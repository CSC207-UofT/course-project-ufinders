package User;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Objects;
import Journal.JournalUI;
import Events.EventUI;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import Marketplace.User_Controls;

import javax.swing.filechooser.FileSystemView;


public class User_UI {

    public static boolean check_account(String userID){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + File.separator +"Documents" + File.separator  + "userData");
        File myFile = new File(dir.getPath(),userID + ".txt");
        if (!myFile.exists()) {
            System.out.println("Username does not exist. Try Again or Sign-Up");
            System.exit(0);
            return false;
        }
        else{
            return true;
        }

    }


    public static String retrieve_account(String userID){
        return FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + File.separator +"Documents" + File.separator  + "userData" + File.separator +
                userID + ".txt";

    }


    public static void main(String[] args) throws ResponseException, IOException, NotFound {
        Scanner read = new Scanner(System.in);

        // Asks user if they want to exit the UserUI
        System.out.println("Would you like to Sign-In, Sign-Up, or Exit?");
        String id;
        String password;
        id = "";
        password = "";
        String choice = read.nextLine();
        // Program keeps the user on the login section until they exit

        if(Objects.equals(choice, "Sign-In")) {

            System.out.println("Username:");
            String input = read.nextLine();
            if (Objects.equals(input, "Exit"))
                System.exit(0);
            else
                id = input;

            if (check_account(id))
                System.out.println("Password:");
            input = read.nextLine();
            String user_file = retrieve_account(id);
            if (Objects.equals(input, "Exit"))
                System.exit(0);
            else
                password = input;
            String stored = User_Controller.read_password(user_file);
            if (!Objects.equals(stored, password)) {
                System.out.println("Incorrect Password Try Again");
                System.exit(0);
            }
            System.out.println("Would you like to access the Journal, Events, or Marketplace section?");
            System.out.println("Type 'journal', 'events', or 'marketplace'");
            String user_input = read.nextLine();
            // Lead the user to Journal if they chose Journal
            if (Objects.equals(user_input, "journal")) {
                String[] dir_path = {User_Controller.read_directory(user_file)};
                JournalUI.main(dir_path);

            }
            // Lead the user to Events if they chose the events tab
            else if (Objects.equals(user_input, "events")) {
                String[] arguments = new String[]{"hi"};
                EventUI.main(arguments);
            }
            else if (Objects.equals(user_input, "marketplace")) {
                //User_Controls.intro();
            }
        }
        if(Objects.equals(choice, "Sign-Up")) {

            System.out.println("Enter a Username:");
            String input2 = read.nextLine();
            if(Objects.equals(input2, "Exit"))
                System.exit(0);
            else
                id = input2;

            System.out.println("Enter a Password:");
            input2 = read.nextLine();
            if(Objects.equals(input2, "Exit"))
                    System.exit(0);
            else {
                System.out.println("Confirm Password:");
                String confirmation = read.nextLine();
                if(Objects.equals(confirmation, "Exit"))
                    System.exit(0);
                while (!Objects.equals(confirmation, input2))
                    confirmation = read.nextLine();
                if(Objects.equals(confirmation, "Exit"))
                    System.exit(0);

                password = confirmation;
                MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + File.separator +"Documents" + File.separator  + "Journal Entries");
                Create_User.make_user(id, password, dir.getPath());
                System.out.println("Account Created!");

                System.out.println("Would you like to access the Journal, Events, or Marketplace section?");
                System.out.println("Type 'journal', 'events', or 'marketplace'");
                String user_input2 = read.nextLine();
                // Lead the user to Journal if they chose Journal
                if (Objects.equals(user_input2, "journal")) {
                    String[] dir_path = {dir.getPath()};
                    JournalUI.main(dir_path);

                }
                // Lead the user to Events if they chose the events tab
                else if (Objects.equals(user_input2, "events")) {
                    String[] arguments = new String[]{"hi"};
                    EventUI.main(arguments);
                }
                else if (Objects.equals(user_input2, "marketplace")) {
                    User_Controls.intro();
                }
        }
        if (Objects.equals(choice, "Check")){
            System.out.println("Works");
                    }
        if (Objects.equals(choice, "Exit")){
            System.exit(0);
        }
        }
    }
}





