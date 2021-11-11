package Journal;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class JournalTest {
    static Journal journal;
    static JournalFileGateway gateway;
    static MakeDir dir;
    @BeforeClass
    public static void setUp() {
        journal = new Journal();
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        gateway = new JournalFileGateway(dir.getPath());
    }

    @Test(timeout = 1000)
    public void testaddEntry()  {
        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        journal.addEntry("fall walks", walkingEntry);
        assert journal.getEntryFile("fall walks").equals(walkingEntry);
        walkingEntry.delete();

    }
    @Test(timeout = 1000)
    public void testAddMoreThanOneEntry(){
        File duneEntry = new File("Dune.txt");
        gateway.writeToFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun",duneEntry.toString());
        journal.addEntry("Dune", duneEntry);
        Set<String> allEntries = journal.getAllEntryTitles();
        String[] expectedEntryTitles = {"fall walks", "Dune"};
        for (int i = 0; i < allEntries.toArray().length; i += 1){
            allEntries.contains(expectedEntryTitles[i]);

        }
        assert journal.getEntryFile("Dune").equals(duneEntry);
        duneEntry.delete();
    }
    @Test(timeout = 1000)
    public void testdeleteEntry()  {
        journal.deleteEntryFile("Dune");
        Set<String> allEntries = journal.getAllEntryTitles();
        assert !allEntries.contains("Dune");
    }

    @Test(timeout = 1000)
    public void testdeleteEntryThatDoesntExsist()  {
        journal.deleteEntryFile("happy");
        Set<String> allEntries = journal.getAllEntryTitles();
        assert allEntries.contains("fall walks");
    }

    @Test(timeout = 1000)
    public void testdeleteTillJournalEmpty()  {
        journal.deleteEntryFile("fall walks");
        Set<String> allEntries = journal.getAllEntryTitles();
        assert allEntries.toArray().length == 0;
    }

    @Test(timeout = 1000)
    public void testNoEntriesGetAllEntries()  {

        Set<String> allEntries = journal.getAllEntryTitles();

        assert allEntries.toArray().length == 0;

    }

    @Test(timeout = 1000)
    public void testgetAllEntries()  {
        File duneEntry = new File("Dune.txt");
        gateway.writeToFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun",duneEntry.toString());
        journal.addEntry("Dune", duneEntry);
        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        journal.addEntry("fall walks", walkingEntry);
        Set<String> allEntries = journal.getAllEntryTitles();
        assert allEntries.contains("Dune");
        assert allEntries.contains("fall walks");
        assert allEntries.toArray().length == 2;
        duneEntry.delete();
        walkingEntry.delete();

    }





}
