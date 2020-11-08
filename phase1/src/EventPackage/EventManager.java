package EventPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventManager {

    private List<Event> eventList;
    private Integer eventCounter;

    private static Integer eventIdGenerator = 0;

    /**
     * Creates an instance eventManager that contains all the events in eventList
     * @param eventList The list of events this instance of eventManager should store
     */
    public EventManager(List<Event> eventList, Integer UID) {
        this.eventList = eventList;
        this.eventCounter = eventList.size();
    }

    /**
     * Creates an instance of eventManager with no events
     */
    public EventManager(Integer UID) {
        this.eventList = Collections.emptyList();
        this.eventCounter = 0;
    }

    /**
     * Creates a new event and stores it in eventManager and increments eventCounter if the event is happening in an
     * available room and at an available time.
     * @param eventName Name of new event
     * @param eventRoom Number of room event is occurring at
     * @param eventDate The date and time the event is occurring at
     * @param eventSpeaker The Speaker at this event
     * @return             true if event was created successfully false otherwise
     */
    public boolean createEvent(String eventName, Integer eventRoom, Date eventDate, Integer eventSpeaker) {
        for (Event event: eventList) {
            if (event.getEventRoom().equals(eventRoom))
                if (!dateCompare(eventDate, event.getEventDate())) {
                    return false;
            }
        }
        Event newEvent = new Event(EventManager.eventIdGenerator++, eventName, eventSpeaker, eventDate, eventCounter+1);
        eventList.add(newEvent);
        eventCounter += 1;
        return true;
    }

    /**
     * Helper Method that checks if two dates don't occur with 1 hour of each other
     * @param date1 One of dates to be compared
     * @param date2 The other date to be compared
     * @return      true if they don't occur within 1 hour of each other, false otherwise
     */
    private boolean dateCompare(Date date1, Date date2) {
        if (date1.getYear() != date2.getYear())
            return true;
        else if (date1.getMonth() != date2.getMonth())
            return true;
        else if (date1.getDate() != date2.getDate())
            return true;

        if (Math.abs(date1.getHours() - date2.getHours()) > 1)
            return true;

        if (Math.abs(date1.getHours() - date2.getHours()) == 1) {
            if (date1.getHours() > date2.getHours()) {
                return date1.getMinutes() >= date2.getMinutes();
            }
            else if (date1.getHours() < date2.getHours()) {
                return date1.getMinutes() <= date2.getMinutes();
            }
        }

        return false;
    }

    /**
     * Enrolls current user to event with eventID and returns an integer
     * @param eventID: ID of an event
     * @param userID ID of a user
     * @return 1, if enrollement was successful <p></p>
     *         0, if UID is already enrolled or event is full <p></p>
     *         -1, if event does not exist <p></p>
     */
    public int enroll(int eventID, int userID) {
        for (Event event: this.eventList) {
            if (event.getEventId() == eventID) {
                if(!event.enrolled(userID)) {
                    event.addAttendee(userID);
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * Removes registration of current user to event with eventID and returns an integer
     * @param eventID: ID of an event
     * @param userID ID of a user
     * @return 1, if unenrollement was successful <p></p>
     *         0, if UID is not enrolled <p></p>
     *         -1, if event does not exist <p></p>
     */
    public int unenroll(int eventID, int userID) {
        for (Event event: this.eventList) {
            if (event.getEventId() == eventID) {
                if(event.enrolled(userID)) {
                    event.removeAttendee(userID);
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * Returns a list of events that the user can sign up for
     * @param userID ID of a user
     * @return Returns ArrayList of Events user can sign up for.
     */
    public List<Event> availEvents(int userID) {
        ArrayList<Event> availEventList = new ArrayList<Event>();
        for (Event event: this.eventList) {
            if (!event.enrolled(userID)){
                availEventList.add(event);
            }
        }
        return availEventList;
    }

    /**
     * Returns list of events that user has signed up for
     * @param userID ID of a user
     * @return Returns ArrayList of Events that user has signed up for.
     */
    public List<Event> myEvents(int userID) {
        ArrayList<Event> enrolledEvents = new ArrayList<Event>();
        for (Event event: this.eventList) {
            if (event.enrolled(userID)){
                enrolledEvents.add(event);
            }
        }
        return enrolledEvents;
    }

}