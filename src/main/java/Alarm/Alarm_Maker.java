package Alarm;


public class Alarm_Maker {

    public Alarm createAlarm(String title, String filename){
        Alarm new_alarm = new Alarm(title, filename);
        return new_alarm;
    }
}