package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ViewJournalEntryTest {
    JournalController controller;
    MakeDir dir;
    String title;

    @Before
    public void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        controller  = new JournalController(dir);
        title = "untitled";
    }
    @Test(timeout = 1000)
    public void testViewEntryAllInfoCorrect(){
        String tags = "cs, stress, life";
        String entry = "I think i did good on my midterm";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(info, actualInfo);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoTags(){
        String tags = null;
        String entry = "I think i did good on my midterm";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(info, actualInfo);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoEntry(){
        String tags = "cs, stress, life";
        String entry = null;
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(info, actualInfo);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoEntryAndContent(){
        String tags = null;
        String entry = null;
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(info, actualInfo);

    }

    @Test(timeout = 1000)
    public void testViewEntryTwoEntriesToView(){
        String tags = "cs, stress, life";
        String entry = "I think i did good on my midterm";
        String tagsTwo = "birthday, 51, dad";
        String entryTwo = "Thursday is my dads birthday";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        controller.callCreateEntry("birthday", entryTwo, LocalDate.now(), tagsTwo);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(info, actualInfo);
        String[] infoTwo = controller.callGetEntry("birthday");
        String[] actualInfoTwo = {"birthday", tagsTwo, entryTwo};
        assertEquals(infoTwo, actualInfoTwo);

    }

}
