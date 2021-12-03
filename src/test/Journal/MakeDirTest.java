package Journal;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;

import java.io.File;
import User.MakeDir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MakeDirTest {

    static  MakeDir dir;
    @BeforeClass
    public static void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
    }

    @Test(timeout = 1000)
    public void testGetDir()  {
        File sampleFile = new File(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" + "/" + "JournalEntries");
        assertEquals(sampleFile, dir.getDir());
    }

    @Test(timeout = 1000)
    public void testNewDirMade()  {
        // retrieve file
        assertNotEquals(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries", "JournalEntries");

    }

    @Test(timeout = 1000)
    public void testGetPathOfFile()  {
        // retrieve file
        assertEquals(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries", dir.getPath());
    }
}
