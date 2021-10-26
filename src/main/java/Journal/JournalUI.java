package Journal;

import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class JournalUI {
    public JournalController controller;

    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(dir);
    }

    public void addEntry(){
        Scanner read = new Scanner(System.in);
        System.out.println("Title of entry:");
        String title = read.nextLine();
        System.out.println("Entry:");
        String content = read.nextLine();
        System.out.println("Enter each tag you would like to add seperated by a comma:");
        String tags = read.nextLine();
        LocalDate today = LocalDate.now();
        this.controller.callCreateEntry(title, content, today, tags );

    }

    public void deleteEntry(){
        Scanner read = new Scanner(System.in);
        System.out.println("Title of entry you would like to delete:");
        String title = read.nextLine();
        this.controller.callDeleteEntry(title );

    }

    public void viewEntry(){

    }

    public static void main(String[] args) {
        JournalUI UI = new JournalUI();
        UI.addEntry();
        UI.deleteEntry();


    }
}


