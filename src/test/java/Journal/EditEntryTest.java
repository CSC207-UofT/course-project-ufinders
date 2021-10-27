package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EditEntryTest {

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
        public void testEditEntryNothingEdited(){
            String tags = "cs, stress, life";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, tags,LocalDate.now(), entry);
            String[] actualInfo = {title, tags, entry};
            assertEquals(actualInfo[0], info[0]);
            assertEquals(actualInfo[1], info[1]);
            assertEquals(actualInfo[2], info[2]); // date cannot be edited
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoTags(){
            String tags = "";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, "life, passion, school",LocalDate.now(), entry);
            String[] actualInfo = {title, "life, passion, school", entry};
            assertNotEquals(actualInfo[1], info[1]);
            assertEquals(actualInfo[0], info[0]);
            assertEquals(actualInfo[2], info[2]);
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoEntry(){
            String tags = "cs, stress, life";
            String entry = "";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, tags,LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {title, tags, "trying to figure it out"};
            assertNotEquals(actualInfo[2], info[2]);
            assertEquals(actualInfo[0], info[0]);
            assertEquals(actualInfo[1], info[1]);
        }

        @Test(timeout = 1000)
        public void testEditEntryTitleEditable(){
            String tags = "cs, stress, life";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, "life", tags,LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {"life", tags, entry};
            assertNotEquals(actualInfo[0], info[0]);
            assertEquals(actualInfo[2], info[2]);
            assertEquals(actualInfo[1], info[1]);
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoEntryAndTags(){
            String tags = "";
            String entry = "";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, "life, school, destiny",LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {title, "life, school, destiny", "trying to figure it out"};
            assertNotEquals(actualInfo[1], info[1]);
            assertNotEquals(actualInfo[2], info[2]);
            assertEquals(actualInfo[0], info[0]);
        }


        @Test(timeout = 1000)
        public void testEditEntryTwoEntriesEditing(){
            String tags = "birthday, 52";
            String entry = "its my dads birthday";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, "school", "life, school, destiny",LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {"school", "life, school, destiny", "trying to figure it out"};
            assertNotEquals(actualInfo[1], info[1]);
            assertNotEquals(actualInfo[2], info[2]);
            assertNotEquals(actualInfo[0], info[0]);

            String tagsTwo = "Reading week, fun";
            String entryTwo = "gonna relax this reading week";
            controller.callCreateEntry("Reading Week", entryTwo, LocalDate.now(), tagsTwo);
            String[] infoTwo = controller.callGetEntry("Reading Week");
            controller.callEditEntry("Reading Week", "Reading Week", "break, life, family",
                    LocalDate.now(), "gonna relax this reading week");
            String[] actualInfoTwo = {"Reading Week", "break, life, family", "gonna relax this reading week"};
            assertEquals(actualInfoTwo[0], infoTwo[0]);
            assertEquals(actualInfoTwo[2], infoTwo[2]);
            assertNotEquals(actualInfoTwo[1], info[1]);
        }
}
