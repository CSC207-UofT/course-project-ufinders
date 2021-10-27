package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;

public class DeleteEntryTest {

    JournalController controller;
    MakeDir dir;
    String title;
    Journal journal;

    @Before
    public void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        controller  = new JournalController(dir);
        String tags = "cs, stress, life";
        title = "midterm";
        String entry = "I think i did good on my midterm";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        journal = controller.getJournalManager().getJournal();
    }
    @Test(timeout = 1000)
    public void testDeleteEntry(){
        controller.callDeleteEntry(title);
        assert journal.getEntryFile(title) == null;
    }
}
