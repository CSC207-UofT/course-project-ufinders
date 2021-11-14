package Events;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class EventUI { //The user interface for the events section

    //Events.EventManager Controller that controls how events work
    private EventManager em;

    /**
     * Constructor that initializes the Events.EventUI object and its Events.EventManager attribute.
     * @param userID the utorID of the student
     */
    public EventUI(String userID) throws IOException {
        File userInfo = new File("/course-project-ufinders/src/main/java/userData/" + userID + ".txt");
        em = new EventManager(userID);
        em.loadEvents();
    }

    /**
     * Constructs an event on a calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event AddEvent(String date, String time, String title, String URL) throws IOException {
        return em.addEvent(date, time, title, URL);
    }

    /**
     * Removes an event from this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void RemoveEvent(String date, String time, String title, String URL) throws IOException {
        em.removeSingleEvent(date, time, title, URL);
    }

    /**
     * Reads events matching given keywords from the UofT website
     * @param keywords The string containing the keywords given by the user for the purposes of searching for events.
     */
    public List<Event> SearchEvent(String keywords) throws ResponseException, NotFound {
        return em.searchEvent(keywords);
    }

    /**
     * Removes all events from this student's calendar.
     */
    public void RemoveAllEvents() throws FileNotFoundException {
        em.removeAllEvents();
    }

    /**
     * Retrieves an event from this student's database.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event RetrieveEvent(String date, String time, String title, String URL) {
        return em.retrieveEvent(date, time, title, URL);
    }

    /**
     * Edits an event in this student's database.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @param newDate The new date of the event.
     * @param newTime The new time the event takes place at.
     * @param newTitle The new name of the event.
     * @param newURL The new URL of the event.
     */
    public Event EditEvent(String date, String time, String title, String URL, String newDate, String newTime, String newTitle, String newURL) throws IOException {
        return em.editEvent(date, time, title, URL, newDate, newTime, newTitle, newURL);
    }

    /**
     * Retrieves all events on this student's calendar.
     */
    public List<Event> RetrieveAllEvents() {
        return em.retrieveAllEvents();
    }

    /**
     * Sets an alarm for the specified event on this student's calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     * @param AlarmDate The date the alarm goes off.
     * @param AlarmTime The time the alarm goes off.
     */
    public void SetAlarm(String date, String time, String title, String URL, String AlarmDate, String AlarmTime) {
        em.setAlarm(date, time, title, URL, AlarmDate, AlarmTime);
    }

    /**
     * Removes the alarm from this event on the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public void RemoveAlarm(String date, String time, String title, String URL) {
        em.removeAlarm(date, time, title, URL);
    }

    /**
     * Loads this user's calendar from their database.
     */
    public void LoadEvents() throws IOException {
        em.loadEvents();
    }

    public static void main(String[] args) throws IOException, ResponseException, NotFound {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String user = read.nextLine();
        EventUI eventView = new EventUI(user);

        // Asks user if they want to exit the event program
        System.out.println("Type Exit to leave events, or No: ");
        // Events.main.java.Events.Event program keeps running until the user types exit
        while (!(Objects.equals(read.nextLine(), "Exit"))) {
            // User wants to add an event

            System.out.println("View all events? Y/N:");
            String answer = read.nextLine();
            if (Objects.equals(answer, "Y")) {
                List<Event> eventList = eventView.RetrieveAllEvents();
                EventUI.printEvents(eventList);
                if (eventList.size() == 0) {
                    System.out.println("You have no events.");
                }

                System.out.println("Want to remove an event? Y/N:");
                String remove = read.nextLine();

                if (Objects.equals(remove, "Y")) {
                    System.out.println("Enter index of event (where the first index is 0): ");
                    try {
                        String removeAnswer = read.nextLine();
                        int removeIndex = Integer.parseInt(removeAnswer);
                        if (removeIndex >= eventList.size() || removeIndex < 0) {
                            System.out.println("Invalid entry");
                        } else {
                            eventView.RemoveEvent(eventList.get(removeIndex).getEventDate(), eventList.get(removeIndex).getEventTime(),
                                    eventList.get(removeIndex).getEventTitle(), eventList.get(removeIndex).getEventURL());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index");
                    }
                }
            }

                System.out.println("Want to add an event? Y/N:");
                answer = read.nextLine();
                if (Objects.equals(answer, "Y")) {
                    System.out.println("Name of event:");
                    String title = read.nextLine();
                    System.out.println("date of event (in format YYYY-MM-DD):");
                    String date = read.nextLine();
                    System.out.println("Time of event (in format 'HH:MM'):");
                    String time = read.nextLine();
                    eventView.AddEvent(date, time, title, "");
                }

                System.out.println("Want to search for an event? Y/N:");
                String answer2 = read.nextLine();
                if (Objects.equals(answer2, "Y")) {
                    System.out.println("Type keywords separated by spaces: ");
                    List<Event> searchedEvent = eventView.SearchEvent(read.nextLine());
                    printEvents(searchedEvent);
                    System.out.println("Do you want to add any of these? Y/N: ");
                    String answer3 = read.nextLine();
                    if (Objects.equals(answer3, "Y")) {
                        System.out.println("Enter index of desired event (where the first index is 0): ");
                        try {
                            int answer4 = Integer.parseInt(read.nextLine());
                            if (answer4 >= searchedEvent.size() || answer4 < 0) {
                                System.out.println("Invalid entry");
                            } else {
                                System.out.println("Event added.");
                                eventView.AddEvent(searchedEvent.get(answer4).getEventDate(), searchedEvent.get(answer4).getEventTime(),
                                        searchedEvent.get(answer4).getEventTitle(), searchedEvent.get(answer4).getEventURL());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid entry");
                        }
                    }
                }
                System.out.println("Want to remove all events? Y/N: ");
                String answer5 = read.nextLine();
                if (Objects.equals(answer5, "Y")) {
                    eventView.RemoveAllEvents();
                }
                System.out.println("Type R to restart the cycle: ");



        }
    }

    public static void printEvents(List<Event> eventList) {
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println(MessageFormat.format("{0} on {1} at {2}, URL: {3}", eventList.get(i).getEventTitle(),
                    eventList.get(i).getEventDate(), eventList.get(i).getEventTime(),
                    eventList.get(i).getEventURL()));
        }
    }


}
