package Journal;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

public class JournalWindow {

    public static void main(String[] args) throws IOException {
        JournalController controller = new JournalController();

        JTextField titleInput = new JTextField();
        JTextField entryInput = new JTextField();
        JTextField tagsInput = new JTextField();

        Object[] prompts = {
                "Title:", titleInput,
                "Type you journal entry:", entryInput,
                "Type the tags you would like to add to entry seperated by commas :", tagsInput,
        };
        int option = JOptionPane.showConfirmDialog(null, prompts, "Journal Entry", JOptionPane.OK_CANCEL_OPTION);
        String title = titleInput.getText();
        String entry = entryInput.getText();
        String[] allTags = tagsInput.getText().split(",");
        LocalDate today = LocalDate.now();

        // strip all tags of any whitespace
        for( int i = 0; i < allTags.length; i += 1 ){
            allTags[i] = allTags[i].strip();
        }

        // ask controller to add entry to journal
       controller.callCreateEntry(title, entry, today, allTags);

       // String[] sample = {"apple", "birthday"};
       // controller.callCreateEntry("birthday", "ordering air pods", today, sample);


    }
}
