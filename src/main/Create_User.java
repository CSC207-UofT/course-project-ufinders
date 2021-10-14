import java.util.Date;
import java.util.HashMap;

public class Create_User {

    public static void addCourse(Date date, String time, String title, String user, Boolean print){
        EventManager.addCourseEvent(date, time, title, user, print);
    }

    public static User makeUserObject(String userID, String name){
        HashMap<Date, Event> map = new HashMap<Date, Event>();
        User object = new User(userID, name, "", map);
        return object;
    }
}
