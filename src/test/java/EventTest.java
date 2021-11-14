package java;

import Events.*;
import User.*;
import static org.junit.Assert.*;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventTest {
    EventUI eventUI;

    @Test(timeout = 50)
    public void testGetEventData() throws IOException {
        User_UI.logIn("soltanp3");
        eventUI  = new EventUI("soltanp3");
        Event event = eventUI.AddEvent("2021-12-12", "4:00", "The Event", "www.events.com");

        assertEquals(event.getEventDate(), "2021-12-12");
        assertEquals(event.getEventTime(), "4:00");
        assertEquals(event.getEventTitle(), "The Event");
        assertEquals(event.getEventURL(), "www.events.com");
    }


    @Test(timeout = 5000)
    public void testGetEventFromWebsite() throws ResponseException, NotFound {
        List<Event> eventList = GetEvent.findEvents("Data");
        List<Event> empty = new ArrayList<>();
        assertNotSame(eventList, empty);
    }

}
