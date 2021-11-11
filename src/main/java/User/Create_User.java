package User;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Create_User {

    public static void make_user(String id, String password, String directory){
            User new_user = new User(id, password, directory);
            File f = new File("/course-project-ufinders/src/main/java/userData");
            f.mkdir();
            File userInfo = new File(f, id + ".txt");
            String filename = "/course-project-ufinders/src/main/java/userData/" + id + ".txt";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                writer.write(id);
                writer.newLine();
                writer.write(password);
                writer.newLine();
                writer.write(directory);
                writer.close();//closes the file

            } catch (Exception e) {//if an exception occurs
                System.out.println("Failed to create new user" + e.getMessage());
            }
    }
}