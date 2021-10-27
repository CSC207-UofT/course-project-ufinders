package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void testViewEntryAllInfoMatchesInfoInFile(){
        String tags = "cs, stress, life";
        String entry = "I think i did good on my midterm";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, entry};
        assertEquals(actualInfo[0], info[0]);
        assertEquals(actualInfo[1], info[1]);
        assertEquals(actualInfo[2], info[2]);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoTags(){
        String tags = "";
        String entry = "I think i did good on my midterm";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, "", entry};
        assertEquals(actualInfo[0], info[0]);
        assertEquals(actualInfo[1], info[1]);
        assertEquals(actualInfo[2], info[2]);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoEntry(){
        String tags = "cs, stress, life";
        String entry = "";
        controller.callCreateEntry(title, entry, LocalDate.now(), tags);
        String[] info = controller.callGetEntry(title);
        String[] actualInfo = {title, tags, ""};
        assertEquals(actualInfo[0], info[0]);
        assertEquals(actualInfo[1], info[1]);
        assertEquals(actualInfo[2], info[2]);

    }

    @Test(timeout = 1000)
    public void testViewEntryWithNoEntryAndContent(){
        controller.callCreateEntry(title, "", LocalDate.now(), "");
        String[] info = controller.callGetEntry(title);
        assertEquals(title, info[0]);
        assertEquals("", info[1]);
        assertEquals("", info[1]);

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
        assertNotEquals(actualInfo[0], info[0]);
        assertNotEquals(actualInfo[1], info[1]);
        assertNotEquals(actualInfo[2], info[2]);

        String[] infoTwo = controller.callGetEntry("birthday");
        String[] actualInfoTwo = {"birthday", tagsTwo, entryTwo};
        assertEquals( actualInfoTwo[0], infoTwo[0]);
        assertEquals(actualInfoTwo[1], infoTwo[1]);
        assertEquals(actualInfoTwo[2], infoTwo[2]);

    }

}
