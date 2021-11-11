package User;
package java.Journal;

import java.io.File;
import java.util.Scanner;
import java.util.Objects;
import Journal.JournalUI;
import Events.EventUI;

import javax.swing.filechooser.FileSystemView;


public class User_UI {

    public static boolean check_account(String userID){
        File f = new File("/course-project-ufinders/src/main/java/userData");
        f.mkdir();
        File userInfo = new File("/course-project-ufinders/src/main/java/userData/" + userID + ".txt");
        if (!userInfo.exists()) {
            // userInfo = new File(f, userID + ".txt");
            System.out.println("Username does not exist. Try Again or Sign-Up");
            System.exit(0);
            return false;
        }
        else{
            return true;
        }

    }

    public static String retrieve_account(String userID){
        return "/course-project-ufinders/src/main/java/userData/" + userID + ".txt";

    }

    public static void main(String[] args) {
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
                if(Objects.equals(input, "Exit"))
                    System.exit(0);
                else
                    id = input;

                if(check_account(id))
                    System.out.println("Password:");
                    input = read.nextLine();
                    String user_file = retrieve_account(id);
                    if(Objects.equals(input, "Exit"))
                        System.exit(0);
                    else
                        password = input;
                        String stored = User_Controller.read_password(user_file);
                        if (!Objects.equals(stored, password)){
                            System.out.println("Incorrect Password Try Again");
                            System.exit(0);
                        }
                    System.out.println("Would you like to access the journal or the events tab?");
                    System.out.println("Type 'journal' or 'events'");
                    String user_input = read.nextLine();
                    // Lead the user to Journal if they chose Journal
                    if (Objects.equals(user_input, "journal")) {
                        String dir_path = User_Controller.read_directory(id);
                        MakeDir dir = new MakeDir(dir_path);
                        JournalUI.main(dir);

                    }
                    // Lead the user to Events if they chose the events tab
                    else if (Objects.equals(user_input, "events")) {
                        String[] arguments = new String[]{"hi"};
                        EventUI.main(arguments);
            }

        else if (Objects.equals(choice, "Sign-Up")){

                System.out.println("Enter a Username:");
                String input2 = read.nextLine();
                if(Objects.equals(input2, "Exit"))
                    System.exit(0);
                else
                    id = input2;

                System.out.println("Enter a Password:");
                input = read.nextLine();
                if(Objects.equals(input2, "Exit"))
                    System.exit(0);
                else {
                    System.out.println("Confirm Password:");
                    String confirmation = read.nextLine();
                    if(Objects.equals(confirmation, "Exit"))
                        System.exit(0);
                    while (!Objects.equals(confirmation, input))
                        confirmation = read.nextLine();
                        if(Objects.equals(confirmation, "Exit"))
                            System.exit(0);

                    password = confirmation;
                    MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                            .getHomeDirectory()
                            .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
                    Create_User.make_user(id, password, dir.getPath());
                    System.out.println("Account Created!");

                    System.out.println("Would you like to access the journal or the events tab?");
                    System.out.println("Type 'journal' or 'events'");
                    String user_input2 = read.nextLine();
                    // Lead the user to Journal if they chose Journal
                    if (Objects.equals(user_input2, "journal")) {

                        JournalUI.main(dir);

                    }
                    // Lead the user to Events if they chose the events tab
                    else if (Objects.equals(user_input, "events")) {
                        String[] arguments = new String[]{"hi"};
                        EventUI.main(arguments);
                    }
                }
            }
        else if (Objects.equals(choice, "Exit")){
            System.exit(0);
        }
        }
    }
}




