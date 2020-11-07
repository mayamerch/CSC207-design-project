package EventPackage;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Event{
    private Integer eventId;
    private String eventName;
    private Date eventDate;
    private Integer eventRoom;
    private Integer eventSpeaker;
    private List<Integer> eventAttendees;

    /**
     * Constructs a new EventPackage.Event object
     * @param eventName Name of event, is a String
     * @param eventRoom Number of Room the event is in
     * @param eventDate A Date object
     * @param eventSpeaker The ID of a speaker at an event
     * @param eventId The unique ID of an event
     */
    public Event(String eventName, Integer eventRoom, Date eventDate, Integer eventSpeaker,
                 Integer eventId) {
        this.eventName = eventName;
        this.eventSpeaker = eventSpeaker;
        this.eventDate = eventDate;
        this.eventRoom = eventRoom;
        this.eventAttendees = Collections.emptyList();
        this.eventId = eventId;
    }


    /**
     * Returns the ID of the current event
     * @return      The ID of this Event
     */
    public Integer getEventId() {
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
    public Integer getEventRoom() {
        return eventRoom;
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
     * Set the date the event is going to occur at
     * @param eventDate The new date the event is occurring at
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Set the room number where the event is happening
     * @param eventRoom The room number
     */
    public void setEventRoom(Integer eventRoom) {
        this.eventRoom = eventRoom;
    }


    /**
     * Set the ID of the new speaker for the event
     * @param eventSpeaker The ID of the speaker for this event
     */
    public void setEventSpeaker(Integer eventSpeaker) {
        this.eventSpeaker = eventSpeaker;
    }


    /**
     * Adds an Attendee to an event if he isn't already attending
     * @param AttendeeID ID of Attendee we need to add.
     */
    public void addAttendee(Integer AttendeeID) {
        if (!eventAttendees.contains(AttendeeID))
            eventAttendees.add(AttendeeID);
    }

    /**
     * Remove an Attendee with ID AttendeeID from list of Attendees attending the event if hes attending.
     * @param AttendeeID The ID of the Attendee we need to remove
     */
    public void removeAttendee(Integer AttendeeID) {
        int index = eventAttendees.indexOf(AttendeeID);
        if (index != -1) {
            eventAttendees.remove(index);
        }
    }
}