package User;

import java.util.HashMap;
import java.util.Date;

public class User {
    private String UserID;
    private String UserName;
    private String Course;
    private HashMap<Date, Event> Calendar;

    public User(String id, String name, String course, HashMap<Date, Event> calendar){
        this.setUserID(id);
        this.setUserName(name);
        this.setCourse(course);
        this.setCalendar(calendar);
    }

    public void setUserID(String id){
        UserID = id;
    }

    public void setUserName(String name){
        UserName = name;
    }

    public void setCourse(String course){
        Course = course;
    }

    public void setCalendar(HashMap<Date, Event> calendar){
        Calendar = calendar;
    }

}
