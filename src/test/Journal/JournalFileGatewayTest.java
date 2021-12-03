package Journal;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import User.MakeDir;

import static org.junit.Assert.*;

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
    public void testAddOneFile() {
        // later test what happens if file with title already exists
        File duneEntry = gateway.createFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun");
        File[] allFilesInDir = dir.getDir().listFiles();

        assert allFilesInDir != null;
        assertEquals(duneEntry, allFilesInDir[2]);

        String[] entryInfo = gateway.getEntryInfo(duneEntry);
        String[] expectedEntryInfo = {LocalDate.now().toString(), "Dune", "Tuesday, friends, fun",  "going to watch " +
                "dune on tuesday"};

        for (int i = 0; i < expectedEntryInfo.length; i += 1){
            assertEquals(expectedEntryInfo[i], entryInfo[i]);
        }

        File walkingEntry = gateway.createFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold");
        allFilesInDir = dir.getDir().listFiles();
        assert allFilesInDir != null;
        assertEquals(walkingEntry, allFilesInDir[3]);
        assertEquals(4, allFilesInDir.length);

    }

    @Test(timeout = 1000)
    public void testAddOneFileWithTitleOfFileThatExists() {
        // later test what happens if file with title already exists
        File duneEntry = gateway.createFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun");

        File duneEntry2 = gateway.createFile("Dune", "watched it",  LocalDate.now(),
                "Tuesday, friends, fun");

        File[] allFilesInDir = dir.getDir().listFiles();

        if (allFilesInDir != null) {
            assertEquals(duneEntry, allFilesInDir[2]);
        }
        assertNull(duneEntry2);

        }



    @Test(timeout = 1000)
    public void getInfoTest() {

        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());

        String[] expectedFileInfo = {LocalDate.now().toString(), "fall walks", "fall, pretty, cold",  "went on a fall" +
                " walk with a friend"};
        String[] actualFileInfo = gateway.getEntryInfo(walkingEntry);

        for (int i = 0; i < expectedFileInfo.length; i += 1){
            assertEquals(expectedFileInfo[i], actualFileInfo[i]);
        }
        assert walkingEntry.delete();

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
        assert walkingEntry.delete();

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
        assert walkingEntry.delete();

    }
    @Test(timeout = 1000)
    public void deleteFileTest() {

        File walkingEntry = gateway.createFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold");
        gateway.deleteFile(walkingEntry);
        assert Arrays.stream(Objects.requireNonNull(dir.getDir().listFiles())).noneMatch(walkingEntry::equals);
    }

    @Test(timeout = 1000)
    public void onlyDeleteFileInstructedToTest() {

        gateway.deleteFile(foodEntry);
        assert Arrays.asList(Objects.requireNonNull(dir.getDir().listFiles())).contains(halloweenEntry);

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
        assert walkingEntry.delete();

    }

    @Test(timeout = 1000)
    public void orderInfoWrittenToFileTest() throws IOException {

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
        assert walkingEntry.delete();

    }

    }

