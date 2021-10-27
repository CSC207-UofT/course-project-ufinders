package Journal;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JournalWindow extends WindowAdapter implements ItemListener {
    Choice entry = new Choice();
    HashMap<String, File> files = new HashMap<>();
    Frame Journal = new Frame("Entry Deletion");
    public JournalWindow() {






        Journal.setSize(700, 400);
        Journal.setVisible(true);



        File JournalEntries = new File("/Users/thakshamangalam/Documents/JournalEntries");
        File[] directoryListing = JournalEntries.listFiles();


        for (File journalEntry : directoryListing) {
            files.put(journalEntry.getName(), journalEntry);
            entry.add(journalEntry.getName());
        }
        Journal.add(entry);
        entry.addItemListener(this);
    }
    @Override
        public void itemStateChanged(ItemEvent e) {

        String entryToDelete = entry.getSelectedItem(); // the entry the user choose to delete
        entry.remove(entryToDelete); // remove from possible entries to delete
        files.get(entryToDelete).delete(); // delete the entry that the user choose



    }

    public static void main(String[] args) {
        JournalFileGateway ex = new JournalFileGateway(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" +"Documents" + "/"  + "Journal Entries");
        File returned = ex.addFile("love", "love is hard", LocalDate.now(), "apple, idk");
        String[] st = ex.getInfo(returned);

    }







}