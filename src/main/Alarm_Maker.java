public class Alarm_Maker {

    public static void creator(String title, String filename, Event event){
        Alarm alarm = new Alarm(title, filename);
        event.setAlarm(alarm);
    }

}
