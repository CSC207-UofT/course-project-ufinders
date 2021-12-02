package Journal;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import User.MakeDir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JournalFileGatewayTest {

    static JournalFileGateway gateway;
    static MakeDir dir;
    static File halloweenEntry;
    static File foodEntry;
    @BeforeClass
    public static void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        gateway = new JournalFileGateway(dir.getPath());
        halloweenEntry = gateway.createFile("Halloween", "had fun at the party",  LocalDate.now(),
                "party, friends, drinks");
        foodEntry = gateway.createFile("cooking", "coking up some new food",  LocalDate.now(),
                "creative, delicious");

    }

    @Test(timeout = 1000)
    public void addOneFileTest() {
        // later test what happens if file with title already exists
        File duneEntry = gateway.createFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun");
        File[] allFilesInDir = dir.getDir().listFiles();

        assertEquals(duneEntry, allFilesInDir[2]);

        String[] entryInfo = gateway.getEntryInfo(duneEntry);
        String[] excpectedEntryInfo = {LocalDate.now().toString(), "Dune", "Tuesday, friends, fun",  "going to watch " +
                "dune on tuesday"};

        for (int i = 0; i < excpectedEntryInfo.length; i += 1){
            assertEquals(excpectedEntryInfo[i], entryInfo[i]);
        }

        File walkingEntry = gateway.createFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold");
        allFilesInDir = dir.getDir().listFiles();
        assertEquals(walkingEntry, allFilesInDir[3]);
        assertEquals(4, allFilesInDir.length);

    }


    @Test(timeout = 1000)
    public void getInfoTest() {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());

        String[] excpectedFileInfo = {LocalDate.now().toString(), "fall walks", "fall, pretty, cold",  "went on a fall" +
                " walk with a friend"};
        String[] actualFileInfo = gateway.getEntryInfo(walkingEntry);

        for (int i = 0; i < excpectedFileInfo.length; i += 1){
            assertEquals(excpectedFileInfo[i], actualFileInfo[i]);
        }
        walkingEntry.delete();

    }

    @Test(timeout = 1000)
    public void getInfoFromFileWithMissingInfoTest() {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("", "",  LocalDate.now(),
                "", walkingEntry.toString());

        String[] expectedFileInfo = {LocalDate.now().toString(), "", "",  "" };
        String[] actualFileInfo = gateway.getEntryInfo(walkingEntry);

        for (int i = 0; i < expectedFileInfo.length; i += 1){
            assertEquals(expectedFileInfo[i], actualFileInfo[i]);
        }
        walkingEntry.delete();

    }

    @Test(timeout = 1000)
    public void getInfoFromFileWithMissingSomeInfoTest() {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "I love fall",  LocalDate.now(),
                "", walkingEntry.toString());

        String[] expectedFileInfo = {LocalDate.now().toString(), "fall walks", "",  "I love fall" };
        String[] actualFileInfo = gateway.getEntryInfo(walkingEntry);

        for (int i = 0; i < expectedFileInfo.length; i += 1){
            assertEquals(expectedFileInfo[i], actualFileInfo[i]);
        }
        walkingEntry.delete();

    }
    @Test(timeout = 1000)
    public void deleteFileTest() {

        File walkingEntry = gateway.createFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold");
        gateway.deleteFile(walkingEntry);
        assert !( Arrays.stream(dir.getDir().listFiles()).anyMatch(walkingEntry::equals));
    }

    @Test(timeout = 1000)
    public void onlyDeleteFileInstructedToTest() {

        gateway.deleteFile(foodEntry);
        assert Arrays.stream(dir.getDir().listFiles()).anyMatch(halloweenEntry::equals);

    }

    @Test(timeout = 1000)
    public void writeToFileTest() {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        gateway.getEntryInfo(walkingEntry);
        String[] expectedFileInfo = {LocalDate.now().toString(), "fall walks", "fall, pretty, cold",
                "went on a fall walk with a friend" };
        String[] actualFileInfo = gateway.getEntryInfo(walkingEntry);

        for (int i = 0; i < expectedFileInfo.length; i += 1){
            assertEquals(expectedFileInfo[i], actualFileInfo[i]);
        }
        walkingEntry.delete();

    }

    @Test(timeout = 1000)
    public void orderInfoWriteenToFileTest() throws IOException {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        BufferedReader reader = new BufferedReader(new FileReader(walkingEntry));
        String line = reader.readLine(); // date of entry
        assert line.equals(LocalDate.now().toString());
        line = reader.readLine().strip(); // title of entry
        assert line.equals("fall walks");
        reader.readLine(); // space after title of entry
        line = reader.readLine();
        assert line.equals("Tags: fall, pretty, cold");// tags
        reader.readLine();// space after tags
        line = reader.readLine().strip();
        assert line.equals("went on a fall walk with a friend");// entry
        reader.close();
        walkingEntry.delete();

    }

    }

