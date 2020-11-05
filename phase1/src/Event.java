import java.util.Date;
public class Event{
    private int eventId;
    private String[] eventAttendees;
    private String eventName;
    private String eventSpeaker;
    private Date eventDate;
    private String eventLocation;

    /**
     * Constructs a new Event object
     * @param eventDate: A Date object
     * @param eventLocation: Name of a location, is a String
     * @param eventName: Name of event, is a String
     * @param eventSpeaker: a List of Speakers
     */
    public Event(String eventName, String eventSpeaker, Date eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventSpeaker = eventSpeaker;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.generateID();
    }

    /**
     * Generates an ID for event.
     * TODO: 11/4/2020: Decide ID generation
     */
    public void generateID() {

    }

}