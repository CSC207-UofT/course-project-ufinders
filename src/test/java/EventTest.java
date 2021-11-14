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

    @Test(timeout = 5000)
    public void testGetEventFromWebsite() throws ResponseException, NotFound {
        List<Event> eventList = GetEvent.findEvents("Data");
        List<Event> empty = new ArrayList<>();
        assertNotSame(eventList, empty);
    }

}
