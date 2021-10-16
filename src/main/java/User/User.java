package User;

import java.util.HashMap;
import Events.*;
import java.util.Date;

public class User {
    public String UserID;
    public String UserName;
    public String Course;
    public HashMap<Date, Event> Calendar;

    /**
     * Constructs a USER
     *
     * @param id The user id of the user
     * @param name The name of the user
     * @param course The course of the user
     * @param calendar The calendar of the user
     */
    public User(String id, String name, String course, HashMap<Date, Event> calendar){
        this.setUserID(id);
        this.setUserName(name);
        this.setCourse(course);
        this.setCalendar(calendar);
    }

    /**
     * Sets id for the user
     *
     * @param id The user's id
     */
    public void setUserID(String id){
        UserID = id;
    }

    /**
     * Sets name for user
     *
     * @param name The user's name
     */
    public void setUserName(String name){
        UserName = name;
    }

    /**
     * Sets course for user
     *
     * @param course The user's course
     */
    public void setCourse(String course){
        Course = course;
    }

    /**
     * Sets calendar for the user.
     *
     * @param calendar The user's calendar.
     */
    public void setCalendar(HashMap<Date, Event> calendar){
        Calendar = calendar;
    }

}
