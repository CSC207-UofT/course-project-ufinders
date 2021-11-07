package Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class EventUI { //The user interface for the events section

    //Events.EventManager Controller that controls how events work
    private EventManager em;

    /**
     * Constructor that initializes the Events.EventUI object and its Events.EventManager attribute.
     */
    public EventUI(String userID) {
        File userInfo = new File("/course-project-ufinders/src/main/java/userData/" + userID + ".txt");
        em = new EventManager(userID);
    }

    public Event AddEvent(String date, String time, String title, String URL) throws IOException {
        return em.addEvent(date, time, title, URL);
    }

    public void RemoveEvent(String date, String time, String title, String URL) throws IOException {
        em.removeSingleEvent(date, time, title, URL);
    }

    public void RemoveAllEvents() throws FileNotFoundException {
        em.removeAllEvents();
    }

    public Event RetrieveEvent(String date, String time, String title, String URL) {
        return em.retrieveEvent(date, time, title, URL);
    }

    public List<Event> RetrieveAllEvents() {
        return em.retrieveAllEvents();
    }

    public void SetAlarm(String date, String time, String title, String URL, String AlarmDate, String AlarmTime) {
        em.setAlarm(date, time, title, URL, AlarmDate, AlarmTime);
    }

    public void removeAlarm(String date, String time, String title, String URL) {
        em.removeAlarm(date, time, title, URL);
    }

    public void LoadEvents() throws IOException {
        em.loadEvents();
    }

}

//    public static void main(String[] args) {
//        Scanner read = new Scanner(System.in);
//        EventUI eventView = new EventUI();
//
//        // Asks user if they want to exit the event program
//        System.out.println("Type Exit to leave the events or continue to add events");
//        if (!Objects.equals(read.nextLine(), "exit")) {
//            // Events.Event program keeps running until the user types exit
//
//
//            // User wants to add an event
//
//            System.out.println("View all events? Y/N:");
//            String answer = read.nextLine();
//            if (Objects.equals(answer, "Y")) {
//                eventView.em.displayEvents();
//            }
//            System.out.println("Name of event:");
//            String title = read.nextLine();
//            System.out.println("date of event (in format YYYY-MM-DD):");
//            String date = read.nextLine();
//            int year = Integer.parseInt(date.substring(0, 4));
//            int month = Integer.parseInt(date.substring(5, 7));
//            int day = Integer.parseInt(date.substring(8, 10));
//            Date date2 = new Date(year, month, day);
//            System.out.println("Time of event (in format 'HH:MM'):");
//            String time = read.nextLine();
//            eventView.em.addEvent(date2, time, title, "", "username", true);
//            System.out.println("Type exit to leave the program or continue to keep typing event");
//        }
//
//    }
