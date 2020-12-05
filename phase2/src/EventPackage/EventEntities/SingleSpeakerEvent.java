package EventPackage.EventEntities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SingleSpeakerEvent extends Event implements Serializable, Comparable<Event>, SpeakerEvent{

    private int eventSpeaker;

    /**
     * Constructs a new SingleSpeaker Event object
     * @param eventDate A Date object
     * @param eventRoom Number of Room the event is in.
     * @param eventName Name of event, is a String
     * @param eventSpeaker The ID of a speaker at an event
     * @param eventId The unique ID of an event
     * @param eventDuration The duration in hours of the event
     * @param VIPStatus Whether this event is VIP exclusive or not
     * @param eventCapacity The capacity of this event
     */
    public SingleSpeakerEvent(int eventId, String eventName, int eventCapacity, Date eventDate, int eventRoom, int eventDuration,
                 boolean VIPStatus, int eventSpeaker)
    {
        super(eventId, eventName, eventCapacity, eventDate, eventRoom, eventDuration, VIPStatus);
        this.eventSpeaker = eventSpeaker;
    }


    /**
     * Returns the ID of the Speaker at this event
     * @return      The ID of the Speaker at this event
     */
    public int getEventSpeaker() {
        return eventSpeaker;
    }


    /**
     * Set the ID of the new speaker for the event
     * @param eventSpeaker The ID of the speaker for this event
     */
    public void setEventSpeaker(int eventSpeaker) {
        this.eventSpeaker = eventSpeaker;
    }


    /**
     * Prints SingleSpeaker events in string format
     * @return      SingleSpeaker in string format
     */
    @Override
    public String toString() {
        return "SS" + ";" + eventSpeaker
                + ";" + super.toString();
    }


    /**
     * Returns a list of all participants (attendees + speaker)
     * @return list of participants
     */
    public ArrayList<Integer> getParticipants() {
        ArrayList<Integer> participants = new ArrayList<>();
        participants.add(eventSpeaker);
        participants.addAll(super.getEventAttendees());

        return participants;
    }

    /**
     * return the events type
     * @return "SS" to represent SingleSpeaker events
     */
    public String getEventType() {
        return "Single Speaker";
    }


}
