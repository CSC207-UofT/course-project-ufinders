package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EditEntryTest {
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
        }
        @Test(timeout = 1000)
        public void testEditEntryNothingEdited(){
            String tags = "cs, stress, life";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, tags,LocalDate.now(), entry);
            String[] actualInfo = {title, tags, entry};
            assertEquals(info, actualInfo);
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoTags(){
            String tags = "";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, "life, passion, school",LocalDate.now(), entry);
            String[] actualInfo = {title, "life, passion, school", entry};
            assertNotEquals(info[1], actualInfo[1]);
            assertEquals(info[0], actualInfo[0]);
            assertEquals(info[2], actualInfo[2]);
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoEntry(){
            String tags = "cs, stress, life";
            String entry = "";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, tags,LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {title, tags, "trying to figure it out"};
            assertNotEquals(info[2], actualInfo[2]);
            assertEquals(info[0], actualInfo[0]);
            assertEquals(info[1], actualInfo[1]);
        }

        @Test(timeout = 1000)
        public void testEditEntryTitleEditable(){
            String tags = "cs, stress, life";
            String entry = "I think i did good on my midterm";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, "life", tags,LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {"life", tags, entry};
            assertNotEquals(info[0], actualInfo[0]);
            assertEquals(info[2], actualInfo[2]);
            assertEquals(info[1], actualInfo[1]);
        }

        @Test(timeout = 1000)
        public void testEditEntryWithNoEntryAndTags(){
            String tags = "";
            String entry = "";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, title, "life, school, destiny",LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {title, "life, school, destiny", "trying to figure it out"};
            assertNotEquals(info[1], actualInfo[1]);
            assertNotEquals(info[2], actualInfo[2]);
            assertEquals(info[0], actualInfo[0]);
        }


        @Test(timeout = 1000)
        public void testEditEntryTwoEntriesEditing(){
            String tags = "birthday, 52";
            String entry = "its my dads birthday";
            controller.callCreateEntry(title, entry, LocalDate.now(), tags);
            String[] info = controller.callGetEntry(title);
            controller.callEditEntry(title, "school", "life, school, destiny",LocalDate.now(), "trying to figure it out");
            String[] actualInfo = {"school", "life, school, destiny", "trying to figure it out"};
            assertNotEquals(info[1], actualInfo[1]);
            assertNotEquals(info[2], actualInfo[2]);
            assertNotEquals(info[0], actualInfo[0]);

            String tagsTwo = "Reading week, fun";
            String entryTwo = "gonna relax this reading week";
            controller.callCreateEntry("Reading Week", entryTwo, LocalDate.now(), tagsTwo);
            String[] infoTwo = controller.callGetEntry("Reading Week");
            controller.callEditEntry("Reading Week", "Reading Week", "break, life, family",
                    LocalDate.now(), "gonna relax this reading week");
            String[] actualInfoTwo = {"Reading Week", "break, life, family", "gonna relax this reading week"};
            assertEquals(infoTwo[0], actualInfoTwo[0]);
            assertEquals(infoTwo[2], actualInfoTwo[2]);
            assertNotEquals(infoTwo[1], actualInfo[1]);
        }
}}
