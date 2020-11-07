package EventPackage;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EventManager {

    private List<Event> eventList;
    private Integer eventCounter;


    /**
     * Creates an instance eventManager that contains all the events in eventList
     * @param eventList The list of events this instance of eventManager should store
     */
    public EventManager(List<Event> eventList) {
        this.eventList = eventList;
        this.eventCounter = eventList.size();
    }


    /**
     * Creates an instance of eventManager with no events
     */
    public EventManager() {
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
        Event newEvent = new Event(eventName, eventRoom, eventDate, eventSpeaker, eventCounter + 1);
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
}