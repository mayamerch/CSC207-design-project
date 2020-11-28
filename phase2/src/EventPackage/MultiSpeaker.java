package EventPackage;


import java.util.ArrayList;
import java.util.Date;

public class MultiSpeaker extends Event{

    private ArrayList<Integer> eventSpeakers;

    /**
     * Constructs a new MultiSpeaker Event object
     * @param eventDate A Date object
     * @param eventRoom Number of Room the event is in.
     * @param eventName Name of event, is a String
     * @param eventSpeakers The IDs of the speakers at this event
     * @param eventId The unique ID of an event
     * @param eventDuration The duration in hours of the event
     */
    public MultiSpeaker(int eventId, String eventName, int eventCapacity, Date eventDate, int eventRoom, int eventDuration,
                         boolean VIPStatus, ArrayList<Integer> eventSpeakers)
    {
        super(eventId, eventName, eventCapacity, eventDate, eventRoom, eventDuration, VIPStatus);
        this.eventSpeakers = eventSpeakers;
    }


    /**
     * Returns the IDs of the Speakers at this event
     * @return      The IDs of the Speakers at this event
     */
    public ArrayList<Integer> getEventSpeakers() {
        return eventSpeakers;
    }


    /**
     * Adds the ID of the new speaker to the list of speakers
     * @param eventSpeaker The ID of the speaker to be added
     */
    public void addEventSpeaker(Integer eventSpeaker) {
        eventSpeakers.add(eventSpeaker);
    }

    /**
     * removes a speaker from this event
     * @param eventSpeaker Id of speaker to be removed
     */
    public void removeSpeaker(Integer eventSpeaker) {
        eventSpeakers.remove(eventSpeaker);
    }


    /**
     * Returns whether we can remove a speaker from this event or not
     * @return true if there is more than one speaker so one can be removed, false otherwise
     */
    public boolean canRemove() {
        return eventSpeakers.size() != 1;
    }


    /**
     * Prints MultiSpeaker events in string format
     * @return      MultiSpeaker in string format
     */
    @Override
    public String toString() {
        return "MS" + ";" + eventSpeakers + ";" + super.toString();
    }


    /**
     * Returns a list of all participants (attendees + speaker)
     * @return list of participants
     */
    public ArrayList<Integer> getParticipants() {
        ArrayList<Integer> participants = new ArrayList<>();
        participants.addAll(eventSpeakers);
        participants.addAll(super.getEventAttendees());

        return participants;
    }

    /**
     * return the events type
     * @return "MS" to represent MultiSpeaker events
     */
    public String getEventType() {
        return "MS";
    }

}
