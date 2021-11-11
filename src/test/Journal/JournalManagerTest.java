package Journal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class JournalManagerTest {
    JournalFileGateway gateway;
    MakeDir dir;
    JournalManager manager;

    @Before
    public  void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" + "/" + "JournalEntries");
        gateway = new JournalFileGateway(dir.getPath());
        manager = new JournalManager(gateway);
    }
    @Test(timeout = 1000)
    public void testNoFilesToDeleteDeleteEntry(){
        manager.deleteEntry("hello");
    }
    @Test(timeout = 1000)
    public void testJournalEmptyGetAllEntries(){
       assert manager.GetAllEntries().isEmpty();

    }

    @Test(timeout = 1000)
    public void testNoEntriesGetEntry(){
        String[] expected = new String[4];
       assert Arrays.equals(manager.getEntry("hello"), expected);
    }



    @Test(timeout = 1000)
    public void testNoEntriesYetCreateEntry(){
        manager.createEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        assert manager.GetAllEntries().contains("fall walk");
    }



    @Test(timeout = 1000)
    public void testGetEntry(){
        manager.createEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] entryInfo = manager.getEntry("fall walk");
        String[] expectedInfo = {LocalDate.now().toString(), "fall walk", "fall, night, fun",  "i love our fall walks"};
        for (int i = 0; i < expectedInfo.length; i += 1){
            assert expectedInfo[i].equals(entryInfo[i]);
        }
    }


    @Test(timeout = 1000)
    public void testJournalNotEmptyCreateEntry(){
        manager.createEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        manager.createEntry("confused", "im confused what I want", LocalDate.now(), "high school, unbearable");
        assert manager.GetAllEntries().contains("fall walk");
        assert manager.GetAllEntries().contains("confused");
    }

    @Test(timeout = 1000)
    public void testJournalNotEmptyGetAllEntries(){
        manager.createEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        manager.createEntry("confused", "im confused what I want", LocalDate.now(), "high school, unbearable");
        Set<String> actualInfo = manager.GetAllEntries();
        assert actualInfo.size() == 2;
        assert actualInfo.contains("fall walk");
        assert actualInfo.contains("confused");


    }

    @Test(timeout = 1000)
    public void testDeleteEntry(){
        manager.createEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        manager.createEntry("confused", "im confused what I want", LocalDate.now(), "high school, unbearable");
        manager.deleteEntry("fall walk");
        assert !manager.GetAllEntries().contains("fall walk");
        assert manager.GetAllEntries().contains("confused");
        manager.deleteEntry("confused");
        assert manager.GetAllEntries().isEmpty();
    }







}
