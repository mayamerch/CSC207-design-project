package EventPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventManager {

    private ArrayList<Event> eventList;
    private int nextID;


    /**
     * Creates an instance eventManager that contains all the events in eventList
     * @param eventList The list of events this instance of eventManager should store
     */
    public EventManager(ArrayList<Event> eventList, int nextID) {
        this.eventList = eventList;
        this.nextID = eventList.get(eventList.size() - 1).getEventId() + 1;
    }

    /**
     * Creates an instance of eventManager with no events
     */
    public EventManager() {
        this.eventList = new ArrayList<>();
        this.nextID = 1;
    }

    /**
     * Creates a new event and stores it in eventManager and increments eventCounter if the event is happening in an
     * available room and at an available time. Returns -1 if it wasn't created and returns the ID of the event created
     * if its successful
     * @param eventName Name of new event
     * @param eventRoom Number of room event is occurring at
     * @param eventDate The date and time the event is occurring at
     * @param eventSpeaker The Speaker at this event
     * @return             -1 if event wasn't created and the ID of the event if it was created
     */
    public int createEvent(String eventName, int eventRoom, Date eventDate, int eventSpeaker,
                               int eventDuration) {
        for (Event event: eventList) {
            if ((event.getEventRoom() == (eventRoom)) || (event.getEventSpeaker() == eventSpeaker))
                if (!dateCompare(eventDate, event.getEventDate(), eventDuration, event.getEventDuration())) {
                    return -1;
            }
        }
        Event newEvent = new Event(nextID, eventName, eventSpeaker, eventDate, eventRoom, eventDuration);
        eventList.add(newEvent);
        nextID += 1;
        return nextID - 1;
    }



    private boolean dateCompare(Date date1, Date date2, int duration1, int duration2) {
        if (date1.getYear() != date2.getYear())
            return true;
        else if (date1.getMonth() != date2.getMonth())
            return true;
        else if (date1.getDate() != date2.getDate())
            return true;


        if (date1.getHours() > date2.getHours()) {
            if (date2.getHours() + duration2 > date1.getHours())
                return false;

            if (date2.getHours() + duration2 == date1.getHours())
                return date1.getMinutes() >= date2.getMinutes();

            return true;
        }


        if (date1.getHours() < date2.getHours()) {
            if (date1.getHours() + duration1 > date2.getHours())
                return false;

            if (date1.getHours() + duration1 == date2.getHours())
                return date1.getMinutes() <= date2.getMinutes();

            return true;
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
    public ArrayList<Event> availEvents(int userID) {
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
    public ArrayList<Event> myEvents(int userID) {
        ArrayList<Event> enrolledEvents = new ArrayList<Event>();
        for (Event event: this.eventList) {
            if (event.enrolled(userID)){
                enrolledEvents.add(event);
            }
        }
        return enrolledEvents;
    }


    /**
     * Checks if an event is included in the list of events and removes it if it does. Returns true if it was removed
     * and false if not ffound.
     * @param eventID The ID of the event to be deleted
     * @return        True if event was deleted, false if not found
     */
    public boolean cancelEvent(int eventID) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getEventId() == eventID) {
                eventList.remove(i);
                return true;
            }
        }
        return false;
    }

}