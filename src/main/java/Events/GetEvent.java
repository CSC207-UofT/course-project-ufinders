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

        for(int x = 0; x < keywordList.length; x++) {
            for (int i = 0; i < events.size(); i++) {
                eventName = events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getElement(0).innerHTML();
                if (eventName.contains(keywordList[x])) {
                    URL = events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getChildElements().get(0).toString();
                    URL = URL.substring(9, URL.length() - 2);
                    date = events.toList().get(i).getChildElements().get(1).findAttributeValues("<span content>").get(0).substring(0, 10);
                    newEvent = mde.addEvent(date, "", eventName, URL);
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
     */
    public static boolean compareURL(List<Event> eventList, Event event) {
        for (int i = 0; i < eventList.size(); i++) {
            if (Objects.equals(eventList.get(i).getEventURL(), event.getEventURL())) {
                return false;
            }
        }
        return true;
    }}

//    public static void main(String[] args) throws JauntException{
////        UserAgent userAgent = new UserAgent();        //create new userAgent (headless browser)
////        userAgent.visit("http://google.com");         //visit google
////        userAgent.doc.apply("Gravity Falls").submit();  //apply 'butterflies' to search field, then submit form
////        Elements links = userAgent.doc.findEvery("<h3>").findEvery("<a>");   //find search result links
////        for(Element link : links) {
////            System.out.println(link.getAt("href"));
////        };     //print results
//        String URL;
//        UserAgent eventSearcher = new UserAgent();                       //create new userAgent (headless browser).
//        eventSearcher.visit("https://www.utoronto.ca/events");                       //visit a url
//        //Elements tables = eventSearcher.doc.findFirst("<div class=panel-pane pane-views-panes pane-events-panel-pane6 clearfix>");
//        //System.out.println(tables.size());
//
//        Elements events = eventSearcher.doc.findEvery("<tbody>").findEach("<tr>");
//        for (int i = 0; i < events.size(); i++) {
//            if(events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getElement(0).innerHTML().contains("Coffee")) {
//                System.out.println(events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getElement(0).innerHTML());
//                URL = events.toList().get(i).getChildElements().get(0).findEvery("<a href>").getChildElements().get(0).toString();
//                System.out.println(URL.substring(9, URL.length() - 2));
//                System.out.println((events.toList().get(i).getChildElements().get(1).findAttributeValues("<span content>")).get(0).substring(0, 10));
//            }
//        }
//
//
//    }
//
//
//}
