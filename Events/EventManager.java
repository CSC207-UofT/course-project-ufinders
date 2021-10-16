package Events;

import java.util.Date;

public class EventManager { //The controller for how all events work

    //Events.main.java.Events.MakeDeleteEvent Use Case that contains all events for this user
    private MakeDeleteEvent mde;

    /**
     * Constructor that initializes Events.main.java.Events.EventManager and its Events.main.java.Events.MakeDeleteEvent attribute.
     */
    public EventManager() {
        mde = new MakeDeleteEvent();
    }

    /**
     * Adds an event to the calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param url The URL of the event if it's from the UofT website.
     * @param user The username of the user using this program
     * @param print Whether to print the event upon adding it.
     */
    public void addEvent(Date date, String time, String title, String URL, String user, Boolean print) {
        mde.addEvent(date, time, title, URL, user, print);
    }

    /**
     * Prints all events this object holds.
     */
    public void displayEvents() {
        mde.displayEvents();
    }

}
