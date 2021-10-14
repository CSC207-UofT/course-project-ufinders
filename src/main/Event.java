import java.util.Date;

public class Event {
    private Date EventDate;
    private String EventTime;
    private String EventTitle;
    private String EventURL;
    private Alarm EventAlarm;

    public Event(Date date, String time, String title, String URL) {
        this.setEventDate(date);
        this.setEventTime(time);
        this.setEventURL(URL);
        this.setEventTitle(title);
        this.EventAlarm = AlarmMaker.makeAlarm();
    }

    public void setDate(Date date) {
        EventDate = date;
    }

    public void setTime(String time) {
        String check = String.valueOf(time);
        int firstDigit = check.charAt(0);
        int secondDigit = check.charAt(1);
        int thirdDigit = check.charAt(3);
        int fourthDigit = check.charAt(4);
        int hours = firstDigit*10 + secondDigit;
        int minutes = thirdDigit*10 + fourthDigit;
        if (hours >=0 && hours <= 12 && minutes >= 0 && minutes < 60) {
            this.EventTime = time;
        }
    }

    public void setEventTitle(String title) {
        this.EventTitle = title;
    }

    public void setURL(String URL) {
        if (URL.contains("www.") && (URL.contains((".com")))) {
            EventURL = URL;
        }
    }

    public Date getEventDate() {
        return EventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public String getEventURL() {
        return EventURL;
    }

    public void setAlarm(Alarm alarm) {
        this.EventAlarm = alarm;
    }

    public void offAlarm() {
        this.EventAlarm = null;
    }

}
