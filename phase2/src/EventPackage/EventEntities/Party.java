package EventPackage.EventEntities;

import EventPackage.EventEntities.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Party extends Event implements Serializable, Comparable<Event>{


    /**
     * Creates a Party event with all the data given in parameters
     * @param eventId Id of this event
     * @param eventName Name of the event
     * @param eventCapacity The capacity of this event
     * @param eventDate The date of this event
     * @param eventRoom The id of the room this event takes place in
     * @param eventDuration the duration of the the event in hours
     * @param VIPStatus Whether this event is VIP exclusive or not
     */
    public Party(int eventId, String eventName, int eventCapacity, Date eventDate,
                 int eventRoom, int eventDuration, boolean VIPStatus)
    {
        super(eventId, eventName, eventCapacity, eventDate, eventRoom, eventDuration, VIPStatus);
    }

    /**
     * Prints Party events in string format
     * @return      Party in string format
     */
    @Override
    public String toString() {
        return "P" + ";" + super.toString();
    }


    /**
     * return the events type
     * @return "P" to represent party events
     */
    public String getEventType(){
        return "Party";
    }

    /**
     * returns the list of participants at this event
     * @return list of participants
     */
    public ArrayList<Integer> getParticipants() {
        ArrayList<Integer> participants = new ArrayList<>();
        participants.addAll(super.getEventAttendees());
        return participants;
    }
}