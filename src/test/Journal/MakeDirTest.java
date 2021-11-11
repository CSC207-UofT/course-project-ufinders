package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;

import static org.junit.Assert.assertEquals;

public class MakeDirTest {

    MakeDir dir;
    @Before
    public void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
    }
    @Test(timeout = 1000)
    public void testNewDirMade()  {
        // retrieve file
    }

    @Test(timeout = 1000)
    public void testgetPathofFile()  {
        // retrieve file
        assertEquals(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries", dir.getPath());
    }
}
