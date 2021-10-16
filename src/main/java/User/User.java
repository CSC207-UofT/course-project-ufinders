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

    // Set the UserID
    public void setUserID(String id){
        UserID = id;
    }

    // Set the users name
    public void setUserName(String name){
        UserName = name;
    }

    // Set the course of the user
    public void setCourse(String course){
        Course = course;
    }

    // Set the calendar of the user
    public void setCalendar(HashMap<Date, Event> calendar){
        Calendar = calendar;
    }

}
