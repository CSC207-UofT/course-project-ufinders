package User;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class User_Controller {

    public static boolean check_account(String userID){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userData");
        File myFile = new File(dir.getPath(),userID + ".txt");
        return myFile.exists();

    }

    public static String retrieve_account(String userID){
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator  + "userData" + File.separator +
                userID + ".txt";

    }

    public static String read_password(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); // Read the first line
            // Read and return the second line which is the password
            return reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String read_directory(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        reader.readLine(); // Read the first line
        reader.readLine(); // Read the second line
            // Read and store the third line which is the directory
        return reader.readLine();
    }
        catch (IOException e) {
        e.printStackTrace();
        return null;
    }

    }
}