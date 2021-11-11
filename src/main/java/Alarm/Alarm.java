package Alarm;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Alarm {
    public String Title;
    // The audio file is stored as a string indicating the file name which can be later read
    public String AudioFileName;

    /**
     * Constructs an alarm
     *
     * @param title the name of the alarm
     * @param filename the name of the alarm's audio file
     */

    public Alarm(String title, String filename){
        this.Title = title;
        this.AudioFileName = filename;
    }

    public void setTitle(String title){
        Title = title;
    }

    public void setAudioFileName(String filename){
        AudioFileName = filename;

    }

    public String getTitle(String title){
        return Title;
    }

    public String getAudioFileName(String filename){
        return AudioFileName;
    }

    public void playAlarm(String filename){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        }
        catch (Exception exc)
        {
            System.out.println("Error");
        }
    }
}

