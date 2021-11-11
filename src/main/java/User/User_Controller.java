package User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User_Controller {

    public static String read_password(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); // Read the first line
            String password = reader.readLine(); // Read and store the second line which is the password
            return password;
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
        String directory = reader.readLine();   // Read and store the third line which is the directory
        return directory;
    }
        catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }
}