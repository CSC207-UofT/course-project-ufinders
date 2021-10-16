package Events;

import java.util.*;

public class EventUI { //The user interface for the events section

    //Events.EventManager Controller that controls how events work
    private EventManager em;

    /**
     * Constructor that initializes the Events.EventUI object and its Events.EventManager attribute.
     */
    public EventUI() {
        em = new EventManager();
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        EventUI eventView = new EventUI();

        // Asks user if they want to exit the event program
        System.out.println("Type Exit to leave the events");
        // Events.Event program keeps running until the user types exit
        while(!(Objects.equals(read.nextLine(), "Exit"))){

            // User wants to add an event

            System.out.println("View all events? Y/N:");
            String answer = read.nextLine();
            if (Objects.equals(answer, "Y")) {
                eventView.em.displayEvents();
            }
            System.out.println("Name of event:");
            String title = read.nextLine();
            System.out.println("date of event (in format YYYY-MM-DD):");
            String date = read.nextLine();
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));
            Date date2 = new Date(year, month, day);
            System.out.println("Time of event (in format 'HH:MM'):");
            String time = read.nextLine();
            eventView.em.addEvent(date2, time, title, "", "username", true);
            System.out.println("Type exit to leave the program or continue to keep typing event");
        }

    }

}
