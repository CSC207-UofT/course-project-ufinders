package Journal;

import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;

public class JournalUI {
    public JournalController controller;

    public JournalUI(){
        MakeDir dir = new MakeDir(FileSystemView.getFileSystemView()
                        .getHomeDirectory()
                        .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        this.controller = new JournalController(dir);
    }

    public static void main(String[] args) {
    JournalUI jc= new JournalUI();
    }
}


