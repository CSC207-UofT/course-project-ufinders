package java.User;
import java.Events.*;
import java.util.Date;

public class User_Controller {

    public static void addtousercalendar(Date date, String time, String title, String user, Boolean print){
        EventManager.addCourseEvent(date, time, title, user, print);
    }

    public static void usercreate(String userID, String name){
        Create_User.makeUserObject(userID, name);
    }
}
