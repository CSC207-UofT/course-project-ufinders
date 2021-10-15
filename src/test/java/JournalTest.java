package java;

import java.Journal.JournalController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class JournalTest {
    JournalController controller;
    @Before
    public void setUp() {
        controller  = new JournalController();
    }
    @Test(timeout = 1000)
    public void testAddEntry(){
        ArrayList<String> tags = new ArrayList<>() ;
        tags.add("cs");
        tags.add("stress");
        tags.add("life");

        String actualEntry = controller.callCreateEntry("midterm", "I think i did good on my midterm",
                LocalDate.now(), tags );
        String expectedEntry = "date: " + LocalDate.now() +  '\n' +  "title: midterm"  + '\n'
                + "tags: cs,stress,life"+  '\n' + '\n' +  "I think i did good on my midterm";
        assertEquals(expectedEntry, actualEntry);

    }
}
