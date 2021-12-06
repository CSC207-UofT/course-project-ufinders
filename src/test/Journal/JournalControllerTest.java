package Journal;

import org.junit.Before;
import org.junit.Test;
import User.MakeDir;

import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.util.Arrays;


public class JournalControllerTest {
    JournalFileGateway gateway;
    MakeDir dir;
    JournalController controller;

    @Before
    public  void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" + "/" + "Journal Entries");
        gateway = new JournalFileGateway(dir.getPath());
        controller = new JournalController(gateway);
    }

    @Test(timeout = 1000)
    public void testCallCreateEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] expectedInfo = {LocalDate.now().toString(), "fall walk", "fall, night," +
                " fun", "i love our fall walks"};
        String[] actualInfo = controller.callGetEntry("fall walk");
        for (int i = 0; i < expectedInfo.length; i += 1){
            assert expectedInfo[i].equals(actualInfo[i]);
        }
        controller.callDeleteEntry("fall walk");
    }

    @Test(timeout = 1000)
    public void testCallDeleteEntryEntryNotThere(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callDeleteEntry("fall time");
        assert controller.callGetAllEntries().contains("fall walk");
        controller.callDeleteEntry("fall walk");
    }

    @Test(timeout = 1000)
    public void testCallDeleteEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callDeleteEntry("fall walk");
        assert controller.callGetAllEntries().isEmpty();
    }

    @Test(timeout = 1000)
    public void testCallGetEntryEntryNotThere(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        assert Arrays.equals( controller.callGetEntry("fall time"), new String[4]);
        controller.callDeleteEntry("fall walk");

    }

    @Test(timeout = 1000)
    public void testCallGetEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] actual =  controller.callGetEntry("fall walk");
        String[] expected = {LocalDate.now().toString(), "fall walk", "fall, night, fun", "i love our fall walks"};
        assert Arrays.equals( actual, expected);
        controller.callDeleteEntry("fall walk");
    }

    @Test(timeout = 1000)
    public void testCallEditEntryNoEntryToEdit(){
        controller.callEditEntry("fall walk", "fall confusion", "i love our fall walks",
                LocalDate.now(), "fall, night");
        assert controller.callGetAllEntries().isEmpty();

    }

    @Test(timeout = 1000)
    public void testCallEditEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] beforeEdit  = controller.callGetEntry("fall walk");
        assert controller.callGetAllEntries().contains("fall walk");
        controller.callEditEntry("fall walk", "fall confusion", "fall, night",
                LocalDate.now(), "i love our fall walks but it caused trouble");

        String[] afterEdit = controller.callGetEntry("fall confusion");
        assert !controller.callGetAllEntries().contains("fall walk");
        assert controller.callGetAllEntries().contains("fall confusion");
        assert !Arrays.equals(beforeEdit, afterEdit);
        controller.callDeleteEntry("fall confusion");


    }
    @Test(timeout = 1000)
    public void testCallEditEntryNoEdit(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] beforeEdit = controller.callGetEntry("fall walk");
        controller.callEditEntry("fall walk", "fall walk","fall, night, fun"  , LocalDate.now(), "i love our fall walks");
        String[] afterEdit = controller.callGetEntry("fall walk");
        assert Arrays.equals(beforeEdit, afterEdit);
        controller.callDeleteEntry("fall walk");

    }

    @Test(timeout = 1000)
    public void testCallGetAllEntriesNoEntriesToGet(){
        assert controller.callGetAllEntries().isEmpty();
    }

    @Test(timeout = 1000)
    public void testCallGetAllEntries(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callCreateEntry("ignorance", "still trying to figure it out", LocalDate.now(), "tomorrow, night," +
                " crazy");
        assert controller.callGetAllEntries().size() == 2;
        assert controller.callGetAllEntries().contains("fall walk");
        assert controller.callGetAllEntries().contains("ignorance");
        controller.callDeleteEntry("fall walk");
        controller.callDeleteEntry("ignorance");
    }
}