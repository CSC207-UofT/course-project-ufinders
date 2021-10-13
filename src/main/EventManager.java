import java.util.Date;

public class EventManager {

    public static void addEvent(Date date, String time, String title, String URL, String user, Boolean print) {

        MakeDeleteEvent.makeEvent(date, time, title, URL, user, print);
    }

    public static void addCustomEvent(Date date, String time, String title, String URL, String user, Boolean print) {
        MakeDeleteEvent.makeCustomEvent(date, time, title, URL, user, print);
    }

    public static void deleteEvent(Date date, String time, String title, String URL, String user) {
        MakeDeleteEvent.removeEvent(date, time, title, URL, user);
    }

    public static void addCourseEvent(Date date, String time, String title, String user, Boolean print) {
        MakeDeleteEvent.makeCourseEvent(date, time, title, user, print);
    }

    public static void addAlarm(Date date, String time, String title, String URL, String user) {
        MakeDeleteEvent.makeAlarm(date, time, title, URL, user);
    }

    public static void deleteAlarm(Date date, String time, String title, String URL, String user) {
        MakeDeleteEvent.removeAlarm(date, time, title, URL, user);
    }

}
