package EventPackage;

import java.util.Date;
import java.util.List;

public class Event{
    private static int idCounter;
    private int eventId;
    private String eventName;
    private Date eventDate;
    private Integer eventLocation;
    private Integer eventSpeaker;
    private List<Integer> eventAttendees;

    /**
     * Constructs a new EventPackage.Event object
     * @param eventDate: A Date object
     * @param eventLocation: Number of Room the event is in.
     * @param eventName: Name of event, is a String
     * @param eventSpeaker: The ID of a speaker at an event
     * @param eventAttendees: A list of IDs of Attendees of the event
     */
    public Event(String eventName, Integer eventSpeaker, Date eventDate, Integer eventLocation,
                 List<Integer> eventAttendees) {
        this.eventName = eventName;
        this.eventSpeaker = eventSpeaker;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventAttendees = eventAttendees;
        this.eventId = idCounter;
        idCounter += 1;
    }


    /**
     * Returns the ID of the current event
     * @return      The ID of this Event
     */
    public int getEventId() {
        return eventId;
    }


    /**
     * Returns the name of the current event
     * @return      The name of this Event
     */
    public String getEventName() {
        return eventName;
    }


    /**
     * Returns the date of the current event
     * @return      The date of this Event
     */
    public Date getEventDate() {
        return eventDate;
    }


    /**
     * Returns the number of the room the current event is held
     * @return      The number of the room the event is held in
     */
    public Integer getEventLocation() {
        return eventLocation;
    }


    /**
     * Returns the List of the IDs of the Attendees attending the event
     * @return      List of IDs of Attendees attending
     */
    public List<Integer> getEventAttendees() {
        return eventAttendees;
    }


    /**
     * Returns the ID of the Speaker at this event
     * @return      The ID of the Speaker at this event
     */
    public Integer getEventSpeaker() {
        return eventSpeaker;
    }


    /**
     * Adds an Attendee to an event by adding its ID to the list Attendees attending the event
     * @param AttendeeID: List of IDs of Attendees we need to add.
     */
    public void AddAttendee(Integer AttendeeID) {
        eventAttendees.add(AttendeeID);
    }


}