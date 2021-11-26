package Events;

import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetEvent {
    //Class that gets event from UofT website

    /**
     * Reads events matching given keywords from the UofT website
     * @param keywords The string containing the keywords given by the user for the purposes of searching for events.
     * @return The list of events matching the keyword search.
     */
    public static List<Event> findEvents(String keywords) throws ResponseException, NotFound {
        List<Event> eventList = new ArrayList<>();
        MakeDeleteEvent mde = new MakeDeleteEvent();
        String eventName;
        Event newEvent;
        String URL;
        String date;
        UserAgent eventSearcher = new UserAgent();
        eventSearcher.visit("https://www.utoronto.ca/events");
        String[] keywordList = keywords.split(" ");
        Elements events = eventSearcher.doc.findEvery("<tbody>").findEach("<tr>");

        for (String s : keywordList) {
            for (int i = 0; i < events.size(); i++) {
                eventName = events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getElement(0).innerHTML();
                if (eventName.contains(s)) {
                    URL = events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getChildElements().get(0).toString();
                    URL = URL.substring(9, URL.length() - 2);
                    date = events.toList().get(i).getChildElements().get(1).findAttributeValues("<span content>").get(0).substring(0, 10);
                    newEvent = mde.addEvent(date, "", eventName, URL);
                    System.out.println(URL);
                    if (GetEvent.compareURL(eventList, newEvent)) {
                        eventList.add(newEvent);
                    }
                }
            }
        }
        return eventList;
    }


    /**
     * Sees if a URL for a found event has already been stored to be returned to the user to prevent duplicate events.
     * @param eventList The list of events that will be returned to the user.
     * @param event The event that is being checked to see if it's already going to be returned to the user.
     * @return A boolean signifying if the URL has already been stored.
     */
    public static boolean compareURL(List<Event> eventList, Event event) {
        for (Event value : eventList) {
            if (Objects.equals(value.getEventURL(), event.getEventURL())) {
                return false;
            }
        }
        return true;
    }}
