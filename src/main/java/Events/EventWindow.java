package Events;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class EventWindow extends JFrame implements PropertyChangeListener {
    //The main event window

    private static final long serialVersionUID = 1L;
    //the TextField for typing the date
    JFormattedTextField  textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
    //The EventUI object representing the user's inputs
    EventUI eventInterface;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            EventWindow window;
            try {
                window = new EventWindow(args);
                window.setVisible(true);
            }
            catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    /**
     * A  constructor that constructs the event window.
     * @param userID The userID of the user.
     */
    public EventWindow(String[] userID) throws IOException {
        eventInterface = new EventUI("mkvacs");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(368, 362);
        setTitle("UFinders");

        Container cp = getContentPane();
        FlowLayout flowLayout = new FlowLayout();

        cp.setLayout(flowLayout);


        textField.setValue(new Date());
        textField.setPreferredSize(new Dimension(130, 30));

        // display the window with the calendar
        CalendarWindow calendarWindow = new CalendarWindow(eventInterface);

        //wire a listener for the PropertyChange event of the calendar window
        calendarWindow.addPropertyChangeListener(this);


        JButton calendarButton = new JButton("Pick a Date");
        JButton addButton = new JButton("Add Event");
        JButton searchButton = new JButton("Search Events");
        JButton clearButton = new JButton("Clear Calendar");

        calendarButton.addActionListener(new ActionListener()
        {
            /**
             * Upon clicking the Pick a Date button, a calendar is displayed that can be cycled through. Double clicking
             * opens the events for that day.
             */
            public void actionPerformed(ActionEvent e)
            {
                //render the calendar window below the text field
                calendarWindow.setLocation(textField.getLocationOnScreen().x, (textField.getLocationOnScreen().y + textField.getHeight()));
                //get the Date and assign it to the calendar
                Date d = (Date)textField.getValue();

                calendarWindow.resetSelection(d);
                calendarWindow.setUndecorated(true);
                calendarWindow.setVisible(true);
            }
        });

        addButton.addActionListener(new ActionListener()
        {
            /**
             * Upon clicking the add event button, creates a pop up window for the user to add an event with a title,
             * time, event, and whether its a course or not. If it is a course, the program will create separate events
             * for each week until the next May.
             */
            public void actionPerformed(ActionEvent e)
            {
                String[] eventData = this.addEntryPopUp(new String[]{"", "", "", ""});
                try {
                    if (this.isValidDate(eventData[0])) {
                        eventInterface.addEvent(eventData[0], eventData[1], eventData[2], "");


                        if (Objects.equals(eventData[3], "Y")) {
                            Calendar calendar = Calendar.getInstance();
                            String[] undesirable = {"01", "02", "03", "04", "05", "06", "07"};
                            int year = Integer.parseInt(eventData[0].substring(0, 4));
                            int month = Integer.parseInt(eventData[0].substring(5, 7));
                            int day = Integer.parseInt(eventData[0].substring(8, 10));
                            Date eventDate = new GregorianCalendar(year, month - 1, day).getTime();
                            calendar.setTime(eventDate);
                            while (!calendar.getTime().toString().startsWith("May", 4) ||
                                    !Arrays.asList(undesirable).contains(calendar.getTime().toString().substring(8, 10))) {
                                calendar.add(Calendar.DAY_OF_YEAR, 7);
                                Date date = calendar.getTime();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String strDate = dateFormat.format(date);
                                String newDate = strDate.substring(0, 10);
                                eventInterface.addEvent(newDate, eventData[1], eventData[2], "");
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            /**
             * Creates a pop-up window where the user can type in the time, title, date, and course status of an event
             * @param defaultEntryInput Contains default entry data for the time, title, date, and course info of the event
             * @return an array in the format [date of date, time, title, course] which are attributes of the event
             */
            public String[] addEntryPopUp(String[] defaultEntryInput) {
                JTextField title = new JTextField();
                JTextField date = new JTextField();
                JTextField time = new JTextField();
                JTextField course = new JTextField();
                title.setText(defaultEntryInput[0]);
                date.setText(defaultEntryInput[1]);
                time.setText(defaultEntryInput[2]);
                course.setText(defaultEntryInput[3]);
                Object[] message = {
                        "Title:", title,
                        "Date (YYYY-MM-DD):", date,
                        "Time:", time,
                        "Course? Y/N:", course};
                ImageIcon icon = new ImageIcon("journaling.jpg");
                int option = JOptionPane.showConfirmDialog(null, message, defaultEntryInput[0],
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,  icon); // will be used later
                return new String[]{date.getText(), time.getText(), title.getText(), course.getText()};
            }

            /**
             * Checks if given string represents a date.
             * @param date The date of the event.
             * @return a boolean signifying whether the string is a date or not.
             */
            public boolean isValidDate(String date) {
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                try {
                    sdf.parse(date);
                } catch (ParseException e) {
                    return false;
                }
                return true;
            }
        });

        searchButton.addActionListener(new ActionListener()
        {
            /**
             * Creates a pop-up window where the user can type in the keywords for an event they would like to find.
             * The program will then search the UofT website for matching events, giving the user the choice to select
             * one.
             */
            public void actionPerformed(ActionEvent e)
            {
                String[] eventData = this.searchPopUp(new String[]{""});
                List<Event> eventList = null;
                try {
                    if (!Objects.equals(eventData[0], "")) {
                        eventList = eventInterface.searchEvent(eventData[0]);
                    }
                } catch (ResponseException | NotFound ex) {
                    ex.printStackTrace();
                }
                List<Object> message = new ArrayList<>();
                JTextField index = new JTextField();
                index.setText("");
                for (int i = 0; i < Objects.requireNonNull(eventList).size(); i++) {
                    Event event = eventList.get(i);
                    message.add(i + 1 + ". ");
                    message.add("Title: " + event.getEventTitle() + "\n");
                    message.add("Date: " + event.getEventDate() + "\n");
                    message.add("URL: " + event.getEventURL() + "\n");

                }
                message.add("Enter the number of the event you want to add. Leave Empty if none of them.");
                message.add(index);
                ImageIcon icon = new ImageIcon("journaling.jpg");
                int option = JOptionPane.showConfirmDialog(null, message.toArray(), "",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,  icon);
                if (!Objects.equals(index.getText(), "")) {
                    Event selectedEvent = eventList.get(Integer.parseInt(index.getText()) - 1);
                    try {
                        eventInterface.addEvent(selectedEvent.getEventDate(), "",
                                selectedEvent.getEventTitle(), selectedEvent.getEventURL());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            /**
             * Creates a pop up window where the user types in the keywords of the event they desire.
             * @param defaultEntryInput The default keywords for the event.
             * @return the keywords entered by the user.
             */
            public String[] searchPopUp(String[] defaultEntryInput) {
                JTextField keywords = new JTextField();
                keywords.setText(defaultEntryInput[0]);
                Object[] message = {
                        "Enter Keywords (Capitals are important!):", keywords,
                        };
                ImageIcon icon = new ImageIcon("journaling.jpg");

                int option = JOptionPane.showConfirmDialog(null, message, defaultEntryInput[0],
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,  icon); // will be used later

                return new String[]{keywords.getText()};
            }
        });

        clearButton.addActionListener(new ActionListener()
        {
            /**
             * Upon clicking the Clear Calendar button, every event in the user's calendar is removed.
             */
            public void actionPerformed(ActionEvent e)
            {
                try {
                    eventInterface.removeAllEvents();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //add the UI controls to the ContentPane
        cp.add(textField);
        cp.add(calendarButton);
        cp.add(addButton);
        cp.add(searchButton);
        cp.add(clearButton);
        cp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


    }

    /**
     * Gets the selected date from the calendar control and sets it to the text field.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {

        if (event.getPropertyName().equals("selectedDate")) {

            java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
            Date selDate =  cal.getTime();
            textField.setValue(selDate);
        }

    }

}

