package User;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Objects;
import Journal.JournalUI;
import Events.EventUI;


public class User_UI {

    public static void logIn(String userID) throws IOException {
        File f = new File("/course-project-ufinders/src/main/java/userData");
        f.mkdirs();
        System.out.println(f.exists());
        File userInfo = new File("/course-project-ufinders/src/main/java/userData/" + userID + ".txt");
        if (!userInfo.exists()) {
            userInfo = new File(f, userID + ".txt");
        }

    }

    public static void main(String[] args) throws IOException {
        User_UI.logIn("soltanp3");
    }
}




