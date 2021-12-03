package Journal;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalDate;
import java.util.Set;
import User.MakeDir;



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
    public void testAddEntry()  {
        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        journal.addEntry("fall walks", walkingEntry);
        assert journal.getEntryFile("fall walks").equals(walkingEntry);
       journal.deleteEntryFile("fall walks");
       assert walkingEntry.delete();


    }
    @Test(timeout = 1000)
    public void testAddMoreThanOneEntry(){
        File walkingEntry = new File("fall walks.txt");
        gateway.writeToFile("fall walks", "went on a fall walk with a friend",  LocalDate.now(),
                "fall, pretty, cold", walkingEntry.toString());
        journal.addEntry("fall walks", walkingEntry);
        File duneEntry = new File("Dune.txt");
        gateway.writeToFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun",duneEntry.toString());
        journal.addEntry("Dune", duneEntry);
        Set<String> allEntries = journal.getAllEntryTitles();
        String[] expectedEntryTitles = {"fall walks", "Dune"};
        for (int i = 0; i < allEntries.toArray().length; i += 1){
            assert allEntries.contains(expectedEntryTitles[i]);

        }
        assert journal.getEntryFile("Dune").equals(duneEntry);
        journal.deleteEntryFile("fall walks");
        journal.deleteEntryFile("Dune");
        assert duneEntry.delete();
        assert walkingEntry.delete();
    }
    @Test(timeout = 1000)
    public void testDeleteEntry()  {
        File duneEntry = new File("Dune.txt");
        gateway.writeToFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun",duneEntry.toString());
        journal.addEntry("Dune", duneEntry);
        journal.deleteEntryFile("Dune");
        Set<String> allEntries =  journal.getAllEntryTitles();;
        assert !allEntries.contains("Dune");
        assert duneEntry.delete();
    }

    @Test(timeout = 1000)
    public void testDeleteEntryThatDoesntExist()  {
        journal.deleteEntryFile("happy");
        assert  journal.getAllEntryTitles().toArray().length == 0;
    }

    @Test(timeout = 1000)
    public void testDeleteTillJournalEmpty()  {
        File duneEntry = new File("Dune.txt");
        gateway.writeToFile("Dune", "going to watch dune on tuesday",  LocalDate.now(),
                "Tuesday, friends, fun",duneEntry.toString());
        journal.addEntry("Dune", duneEntry);
        journal.deleteEntryFile("Dune");
        assert duneEntry.delete();
        Set<String> allEntries = journal.getAllEntryTitles();
        assert allEntries.toArray().length == 0;
    }

    @Test(timeout = 1000)
    public void testNoEntriesGetAllEntries()  {
        Set<String> allEntries = journal.getAllEntryTitles();
        assert allEntries.toArray().length == 0;

    }

    @Test(timeout = 1000)
    public void testGetAllEntries()  {
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
        journal.deleteEntryFile("Dune");
        journal.deleteEntryFile("fall walks");
        assert duneEntry.delete();
        assert walkingEntry.delete();

    }





}
