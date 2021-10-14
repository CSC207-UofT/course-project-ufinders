import java.util.Date;
import java.util.HashMap;

public class Alarm {
    private String Title;
    // The audio file is stored as a string indicating the file name which can be later read
    private String AudioFileName;

    public Alarm(String title, String filename){
        this.Title = title;
        this.AudioFileName = filename;
    }
}
