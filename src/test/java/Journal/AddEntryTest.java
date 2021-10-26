package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AddEntryTest {
    JournalController controller;
    MakeDir dir;
    @Before
    public void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        controller  = new JournalController(dir);
    }
    @Test(timeout = 1000)
    public void testAddEntryNoEntriesInJournal(){
        String tags = "cs, stress, life";
        String title = "midterm";
        String entry = "I think i did good on my midterm";

        controller.callCreateEntry(title, entry, LocalDate.now(), tags);


    }
    @Test(timeout = 1000)
    public void testAddEntryEntryInJournal(){
        String tagsTwo = "birthday, fun, party";
        String titleTwo = "dads birthday";
        String entryTwo = "I think his birthday is going to be fun";
        controller.callCreateEntry(titleTwo, entryTwo, LocalDate.now(), tagsTwo);
    }
}
