package Journal;

import org.junit.Before;
import org.junit.Test;
import User.MakeDir;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class JournalControllerTest {
    JournalFileGateway gateway;
    MakeDir dir;
    JournalController controller;

    @Before
    public  void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" + "/" + "JournalEntries");
        gateway = new JournalFileGateway(dir.getPath());
        controller = new JournalController(gateway);
    }

    @Test(timeout = 1000)
    public void testcallCreateEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] expectedInfo = {LocalDate.now().toString(), "fall walk", "fall, night," +
                " fun", "i love our fall walks"};
        String[] actualInfo = controller.callGetEntry("fall walk");
        for (int i = 0; i < expectedInfo.length; i += 1){
            assert expectedInfo[i].equals(actualInfo[i]);
        }
    }

    @Test(timeout = 1000)
    public void testcallDeleteEntryEntryNotThere(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callDeleteEntry("fall time");
        assert controller.callGetAllEntries().contains("fall walk");
    }

    @Test(timeout = 1000)
    public void testcallDeleteEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callDeleteEntry("fall walk");
        controller.callGetAllEntries().isEmpty();
    }

    @Test(timeout = 1000)
    public void testcallGetEntryEntryNotThere(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] expected = new String[4];
        assert Arrays.equals( controller.callGetEntry("fall time"), expected);

    }

    @Test(timeout = 1000)
    public void testcallGetEntry(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
       String[] actual =  controller.callGetEntry("fall walk");
       String[] expected = {LocalDate.now().toString(), "fall walk", "fall, night, fun", "i love our fall walks"};
        assert Arrays.equals( actual, expected);
    }

    @Test(timeout = 1000)
    public void testcallEditEntryNoEntryToEdit(){
        controller.callEditEntry("fall walk", "fall confusion", "i love our fall walks",
                LocalDate.now(), "fall, night");
        assert controller.callGetAllEntries().isEmpty();

    }

    @Test(timeout = 1000)
    public void testcallEditEntry(){
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


    }
    @Test(timeout = 1000)
    public void testcallEditEntryNoEdit(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        String[] beforeEdit = controller.callGetEntry("fall walk");
        controller.callEditEntry("fall walk", "fall walk","fall, night, fun"  , LocalDate.now(), "i love our fall walks");
        String[] afterEdit = controller.callGetEntry("fall walk");
        assert Arrays.equals(beforeEdit, afterEdit);

    }

    @Test(timeout = 1000)
    public void testcallGetAllEntriesNoEntriesToGet(){
        assert controller.callGetAllEntries().isEmpty();
    }

    @Test(timeout = 1000)
    public void testcallGetAllEntries(){
        controller.callCreateEntry("fall walk", "i love our fall walks", LocalDate.now(), "fall, night," +
                " fun");
        controller.callCreateEntry("ignorance", "still trying to figure it out", LocalDate.now(), "tomorrow, night," +
                " crazy");
        assert controller.callGetAllEntries().size() == 2;
        assert controller.callGetAllEntries().contains("fall walk");
        assert controller.callGetAllEntries().contains("ignorance");
    }
}
