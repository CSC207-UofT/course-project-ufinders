import java.util.Date;
import java.util.Objects;

public class MakeDeleteEvent {

    public static void makeEvent(Date date, String time, String title, String URL, String user, Boolean print) {
        if (MakeDeleteEvent.getEvent() == "") {
            Event event = new Event(date, time, title, URL);
            MakeDeleteEvent.addToCalendar(event, user);
            if (print) {
                MakeDeleteEvent.printEvent(event);
            }
        }
        else {
            System.out.println("Event already added");
        }
    }

    public static void makeCustomEvent(Date date, String time, String title, String URL, String user, Boolean print) {
        Event event = new Event(date, time, title, "");
        MakeDeleteEvent.addToCalendar(event, user);
        if (print) {
            MakeDeleteEvent.printEvent(event);
        }
    }


    public static void printEvent(Event event) {
        System.out.println(event.getEventTitle());
        System.out.println(event.getEventDate());
        System.out.println(event.getEventTime());
        if (!Objects.equals(event.getEventURL(), "")) {
            System.out.println(event.getEventURL());
        }
    }

    public static Event getFromCalendar(Date date, String time, String title, String URL, String user) {

    }

    public static void addToCalendar(Event event, String user) {

    }

    public static void removeEvent(Date date, String time, String title, String URL, String user) {

    }

    public static void makeAlarm(Date date, String time, String title, String URL, String user) {
        Event event = MakeDeleteEvent.getFromCalendar(date, time, title, URL, user);
        AlarmMaker.addAlarm(event);
    }

    public static void removeAlarm(Date date, String time, String title, String URL, String user) {
        Event event = MakeDeleteEvent.getFromCalendar(date, time, title, URL, user);
        AlarmMaker.deleteAlarm(event);
    }
}
