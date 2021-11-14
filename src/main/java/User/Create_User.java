package User;



import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Create_User {

    public static void make_user(String id, String password, String directory) throws IOException {
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + File.separator +"Documents" + File.separator  + "userData");

        File myFile = new File(dir.getPath(),id + ".txt");
        myFile.createNewFile();
        String filename = myFile.getPath();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(id);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.write(directory);
            writer.close();//closes the file

        } catch (Exception e) {//if an exception occurs
            System.out.println("Failed to create a new user at " + e.getMessage());
        }
    }

}