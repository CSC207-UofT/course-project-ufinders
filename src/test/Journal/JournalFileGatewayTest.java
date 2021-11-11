package Journal;

import org.junit.Before;
import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class JournalFileGatewayTest {

    JournalFileGateway gateway;
    MakeDir dir;
    @Before
    public void setUp() {
        dir = new MakeDir(FileSystemView.getFileSystemView()
                .getHomeDirectory()
                .getAbsolutePath() + "/" + "Documents" +"/" + "JournalEntries");
        gateway = new JournalFileGateway(dir.getPath());
    }
    @Test(timeout = 1000)
    public void testmoreThanOneItemArraygetStringTags()  {
        String[] tags = {"apple", "orange"};
        assertEquals("apple, orange",gateway.getStringTags(tags).toString());
    }
    @Test(timeout = 1000)
    public void testemptyArrayGetStringTags()  {
        String[] tags = {};
        assertEquals("",gateway.getStringTags(tags).toString());
    }
    @Test(timeout = 1000)
    public void testOneItemArrayGetStringTags()  {
        String[] tags = {"apple"};
        assertEquals("apple",gateway.getStringTags(tags).toString());
    }
}
