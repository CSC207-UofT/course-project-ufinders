//package Journal;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import javax.swing.filechooser.FileSystemView;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Set;
//
//public class JorunalWindowTest { // not sure hot to test pop-up
//   JournalWindow popUP;
//
//    @Before
//    public  void setUp() {
//       popUP = new JournalWindow();
//    }
//
//    @Test(timeout = 1000)
//    public void testviewAndAddEntryPopUpNoDefaults(){
//        popUP.viewAndAddEntryPopUp(new String[4]);
//    }
//
//    @Test(timeout = 1000)
//    public void testviewAndAddEntryPopUpWithDefaults(){
//        String[] entry = {LocalDate.now().toString(), "fall walk", "fall, night, fun", "i love our fall walks"};
//        popUP.viewAndAddEntryPopUp(entry);
//    }
//    @Test(timeout = 1000)
//    public void testviewAndAddEntryPopUpUserInput(){
//
//        String[] actual = popUP.viewAndAddEntryPopUp(new String[4]);
//        String[] expected = { "fall walk", "fall, night, fun", "i love our fall walks"};
//        assert Arrays.equals(actual, expected);
//    }
//
//    @Test(timeout = 1000)
//    public void testviewAndAddEntryPopUpNoUserInput(){
//
//        String[] actual = popUP.viewAndAddEntryPopUp(new String[4]);
//        String[] expected = { null, null, null, null};
//        assert Arrays.equals(actual, expected);
//    }
//
//    @Test(timeout = 1000)
//    public void testAddEntryPopUp(){
//
//        String[] actual = popUP.addEntryPopUp();
//        String[] expected = { "fall walk", "fall, night, fun", "i love our fall walks"};
//        assert Arrays.equals(actual, expected);
//    }
//
//    @Test(timeout = 1000)
//
//    public void testDeleteEntryPopUp(){
//
//        Set<String> allEntries = (Set<String>) new ArrayList<String>();
//        allEntries.add("fall walks");
//        allEntries.add("night time");
//        allEntries.add("movies");
//        assert Objects.equals(popUP.deleteEntryPopUp(allEntries), "movies");
//
//
//    }
//
//    public void testDeleteEntryPopUpNoEntry(){
//
//        Set<String> allEntries = (Set<String>) new ArrayList<String>();
//        popUP.deleteEntryPopUp(allEntries);
//
//    }
//
//    public void testViewEntryPopUp(){
//
//        Set<String> allEntries = (Set<String>) new ArrayList<String>();
//        allEntries.add("fall walks");
//        allEntries.add("night time");
//        allEntries.add("movies");
//        assert Objects.equals(popUP.viewEntriesPopUp(allEntries), "movies");
//    }
//
//    public void testViewEntryPopUpNoEntry(){
//
//        Set<String> allEntries = (Set<String>) new ArrayList<String>();
//        popUP.deleteEntryPopUp(allEntries);
//
//    }
//
//    public void testViewUserOptions(){
//
//
//       assert Objects.equals(popUP.viewUserOptions(), "add");
//
//    }
//
//}
