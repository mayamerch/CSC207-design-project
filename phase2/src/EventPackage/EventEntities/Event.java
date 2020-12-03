package EventPackage.EventEntities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Event implements Comparable<Event>, Serializable {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private int eventRoom;
    private int eventCapacity;
    private int eventDuration;
    private ArrayList<Integer> eventAttendees;
    private boolean VIPStatus;



    @Override
    public int compareTo(Event e){
        return this.eventDate.compareTo(e.eventDate);
    }


    /**
     * Constructs a new Event object
     * @param eventDate A Date object
     * @param eventRoom Number of Room the event is in.
     * @param eventName Name of event, is a String
     * @param eventCapacity The capacity of this event
     * @param eventId The unique ID of an event
     * @param eventDuration The duration in hours of the event
     * @param VIPStatus true if this event is VIP Exclusive, false if not
     */
    public Event(int eventId, String eventName, int eventCapacity, Date eventDate, int eventRoom, int eventDuration,
                 boolean VIPStatus)
        {
            this.eventName = eventName;
            this.eventCapacity = eventCapacity;
            this.eventDate = eventDate;
            this.eventRoom = eventRoom;
            this.eventAttendees = new ArrayList<>();
            this.eventId = eventId;
            this.eventDuration = eventDuration;
            this.VIPStatus = VIPStatus;
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
    public int getEventRoom() {
        return eventRoom;
    }


    /**
     * Returns the duration of this event in hours
     * @return      The duration of this event in hours
     */
    public int getEventDuration() {return eventDuration;}


    /**
     * Returns the List of the IDs of the Attendees attending the event
     * @return      List of IDs of Attendees attending
     */
    public ArrayList<Integer> getEventAttendees() {
        return eventAttendees;
    }

    /**
     * Returns whether this event is exclusive to VIP users
     * @return      true if its VIP exclusive, false if not
     */
    public boolean getVIPStatus(){
        return VIPStatus;
    }


    /**
     * Returns the Capacity of this event
     * @return      The Capacity of this event
     */
    public int getEventCapacity() {
        return eventCapacity;
    }


    /**
     * Change VIP status of the event
     * @param status new VIP status of event
     */
    public void setVIPStatus(boolean status){
        VIPStatus = status;
    }


    /**
     * Change the name of an event
     * @param eventName New name of event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
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
     * Change the duration of an event
     * @param eventDuration The new duration of the event
     */
    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }


    /**
     * Change the capacity of an event
     * @param eventCapacity The new capacity of the event
     */
    public void setEventCapacity(int eventCapacity) {
        this.eventCapacity = eventCapacity;
    }


    /**
     * Returns whether a specific user is already attending an event
     * @param AttendeeID The id of the user
     * @return true if he is already attending the event, false if he isn't
     */
    public boolean alreadyAttending(int AttendeeID) {
        return eventAttendees.contains(AttendeeID);
    }

    /**
     * Adds an Attendee to an event if he isn't already attending
     * @param AttendeeID ID of Attendee we need to add.
     */
    public void addAttendee(Integer AttendeeID) {
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
    /**
     * Returns true if userID is enrolled in Event, otherwise false
     * @param userID id of a user
     * @return True if usuer is enrolled, false otherwise.
     */
    public boolean enrolled(int userID) {
        return eventAttendees.contains(userID);
    }


    /**
     * Returns whether this event is full or not
     * @return      true if its full, false otherwise
     */
    public boolean isFull(){
        return eventAttendees.size() >= eventCapacity;
    }

    /**
     * Prints Event in string format
     * @return      event in string format
     */
    @Override
    public String toString() {
        return (eventId + ";" +
                eventName + ";" +
                eventCapacity + ";" +
                eventDate + ";" +
                eventRoom + ";" +
                eventDuration + ";" +
                VIPStatus) + ";" +
                eventAttendees;
    }


    /**
     * return a String representing the events type
     * @return String representing events type
     */
    public abstract String getEventType();


    /**
     * returns the list of participants at this event
     * @return list of participants
     */
    public abstract ArrayList<Integer> getParticipants();

}
