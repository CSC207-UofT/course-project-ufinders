package Events;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.common.*;


public class CalendarWindow extends JFrame {
    //The display of the calendar

    private static final long serialVersionUID = 1L;

    //The selected date on the calendar
    java.util.Calendar selectedDate = java.util.Calendar.getInstance();
    //The calendar object
    Calendar calendar = new Calendar();
    protected PropertyChangeSupport changeSupport;

    /**
     * A constructor that constructs the calendar window.
     * @param eventInterface The EventUI object representing the inputs of the user.
     */
    public CalendarWindow(EventUI eventInterface)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1000, 1000);
        setTitle("UFinders Calendar");

        changeSupport = new PropertyChangeSupport(this);


        calendar.setTheme(ThemeType.Light);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(calendar, BorderLayout.CENTER);


        calendar.addMouseListener(new MouseAdapter() {
            /**
             * If a date on the calendar is double-clicked, it is selected and so the program will display all the
             * events on that day, giving options to remove or edit those events.
             */
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                {
                    //clear the selection
                    calendar.getSelection().reset();
                    //get the date that was double-clicked
                    DateTime pointedDate = calendar.getDateAt(e.getX(), e.getY());
                    //create a java.util.Calendar instance that points to the selected Date
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(pointedDate.getYear(), pointedDate.getMonth() - 1, pointedDate.getDay());
                    //raise the event
                    setSelectedDate(cal);
                    List<Event> eventList = eventInterface.retrieveAllEvents();
                    List<Event> viewList = new ArrayList<>();

                    List<Object> message = new ArrayList<>();
                    JTextField removeIndex = new JTextField();
                    JTextField editIndex = new JTextField();
                    removeIndex.setText("");
                    editIndex.setText("");
                    String selectedDate = pointedDate.toString();
                    String currDate = Integer.toString(pointedDate.getYear()).substring(0, 2)
                            + selectedDate.substring(6, 8) + "-" + selectedDate.substring(0, 2) + "-"
                            + selectedDate.substring(3, 5);
                    int eventCount = 1;
                    for (int i = 0; i < Objects.requireNonNull(eventList).size(); i++) {
                        Event event = eventList.get(i);
                        if (Objects.equals(event.getEventDate(), currDate)) {
                            viewList.add(event);
                            message.add(eventCount + ". ");
                            message.add("Title: " + event.getEventTitle() + "\n");
                            message.add("Date: " + event.getEventDate() + "\n");
                            message.add("Time: " + event.getEventTime() + "\n");
                            if (!Objects.equals(event.getEventURL(), "")) {
                                message.add("URL: " + event.getEventURL() + "\n");
                            }
                            eventCount += 1;
                        }

                    }
                    message.add("Enter the number of the event you want to remove. Leave Empty if none of them.");
                    message.add(removeIndex);
                    message.add("Enter the number of the event you want to edit. Leave Empty if none of them.");
                    message.add(editIndex);
                    ImageIcon icon = new ImageIcon("journaling.jpg");
                    int option = JOptionPane.showConfirmDialog(null, message.toArray(), "",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,  icon);

                    if (!Objects.equals(removeIndex.getText(), "") && !viewList.isEmpty()) {
                        Event removedEvent = viewList.get(Integer.parseInt(removeIndex.getText())-1);
                        try {
                            eventInterface.removeEvent(removedEvent.getEventTitle(), removedEvent.getEventDate(),
                                    removedEvent.getEventTime(), removedEvent.getEventURL());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (!Objects.equals(editIndex.getText(), "") && editIndex != removeIndex) {
                        Event editedEvent = viewList.get(Integer.parseInt(editIndex.getText())-1);
                        String[] editData = {editedEvent.getEventTitle(), editedEvent.getEventDate(), editedEvent.getEventTime()};
                        String[] newData = this.editPopUp(editData);
                        try {
                            eventInterface.editEvent(editedEvent.getEventDate(), editedEvent.getEventTime(), editedEvent.getEventTitle(),
                                    editedEvent.getEventURL(), newData[0], newData[1], newData[2], editedEvent.getEventURL());

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    dispose();

                }

            }
            /**
             * Creates a pop up window where the user can type in the new title, time, and date of a selected event.
             * @param defaultEntryInput The current time, title, and date of the event.
             * @return The new time, title, and date of the event.
             */
            public String[] editPopUp(String[] defaultEntryInput) {
                JTextField title = new JTextField();
                JTextField date = new JTextField();
                JTextField time = new JTextField();
                title.setText(defaultEntryInput[0]);
                date.setText(defaultEntryInput[1]);
                time.setText(defaultEntryInput[2]);
                Object[] message = {
                        "Title:", title,
                        "Date (YYYY-MM-DD):", date,
                        "Time:", time};
                ImageIcon icon = new ImageIcon("journaling.jpg");
                int option = JOptionPane.showConfirmDialog(null, message, defaultEntryInput[0],
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,  icon); // will be used later
                if (this.isValidDate(date.getText())) {
                    return new String[]{date.getText(), time.getText(), title.getText()};
                }
                else {
                    return new String[]{defaultEntryInput[1], time.getText(), title.getText()};
                }
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
                    if (date.length() != 10) {
                        return false;
                    }
                } catch (ParseException e) {
                    return false;
                }
                return true;
            }
        });

    }


    /**
     *getter of the selectedDate property
     */
    public java.util.Calendar getSelectedDate()
    {
        return selectedDate;

    }

    /**
     *set the selectedDate when typed in the text field
     */
    public void resetSelection(Date date)
    {
        calendar.getSelection().reset();
        calendar.getSelection().set(new DateTime(date), new DateTime(date).addMinutes(2));
        calendar.setDate(new DateTime(date));

    }


    /**
     * Raises the event that the selectedDate property has changed.
     */
    public void setSelectedDate (java.util.Calendar selDate)
    {

        java.util.Calendar oldValue = (java.util.Calendar)selectedDate.clone();
        selectedDate = selDate;

        changeSupport.firePropertyChange("selectedDate",oldValue, selectedDate);

    }

    /**
     * Adds a listener for the PropertyChange event.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }





}