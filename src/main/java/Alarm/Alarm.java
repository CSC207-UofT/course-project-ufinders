package Alarm;

public class Alarm {
    public String Title;
    // The audio file is stored as a string indicating the file name which can be later read
    public String AudioFileName;

    // An alarm object with a title and audio file
    public Alarm(String title, String filename){
        this.Title = title;
        this.AudioFileName = filename;
    }
}
