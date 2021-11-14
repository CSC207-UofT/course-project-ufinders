package Events;

import Alarm.Alarm;
import java.util.Date;

public class Event { //The event object itself

    private String EventDate; //Date of event
    private String EventTime; //Time of event
    private String EventTitle;  //Name of event
    private String EventURL;   //URL of event page
    private Alarm EventAlarm;   //Notification of event

    /**
     * Constructs an event on a calendar.
     *
     * @param date The date of the event.
     * @param time The time the event takes place at.
     * @param title The name of the event.
     * @param URL The URL of the event if it's from the UofT website.
     */
    public Event(String date, String time, String title, String URL) {
        this.setDate(date);
        this.setTime(time);
        this.setURL(URL);
        this.setTitle(title);
    }

    /**
     * Sets date for event.
     *
     * @param date The date of the event.
     */
    public void setDate(String date) {
        EventDate = date;
    }

    /**
     * Sets time for event.
     *
     * @param time The time of the event.
     */
    public void setTime(String time) {
        EventTime = time;
    }

    /**
     * Sets name for event.
     *
     * @param title The title of the event.
     */
    public void setTitle(String title) {
        this.EventTitle = title;
    }

    /**
     * Sets URL for event.
     *
     * @param URL The URL of the event.
     */
    public void setURL(String URL) {
        this.EventURL = URL;
    }

    /**
     * Gets event date.
     */
    public String getEventDate() {
        return EventDate;
    }

    /**
     * Gets event time.
     */
    public String getEventTime() {
        return EventTime;
    }

    /**
     * Gets event title.
     */
    public String getEventTitle() {
        return EventTitle;
    }

    /**
     * Gets event URL.
     */
    public String getEventURL() {
        return EventURL;
    }

    /**
     * Sets alarm notification for event.
     *
     * @param alarm The alarm for the event.
     */
    public void setAlarm(Alarm alarm) {
        this.EventAlarm = alarm;
    }

    /**
     * Turns alarm for event off.
     */
    public void offAlarm() {
        this.EventAlarm = null;
    }
}
