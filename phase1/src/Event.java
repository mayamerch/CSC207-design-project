import java.util.Date;
import java.util.List;

public class Event{
    private static int idCounter;
    private int eventId;
    private List<Integer> eventAttendees;
    private String eventName;
    private Integer eventSpeaker;
    private Date eventDate;
    private String eventLocation;

    /**
     * Constructs a new Event object
     * @param eventDate: A Date object
     * @param eventLocation: Name of a location, is a String
     * @param eventName: Name of event, is a String
     * @param eventSpeaker: The ID of a speaker at an event
     * @param eventAttendees: A list of IDs of Attendees of the event
     */
    public Event(String eventName, Integer eventSpeaker, Date eventDate, String eventLocation,
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
     * Takes in a List of IDs of Attendees that are going to attend the event and adds them to the
     * List of IDs of Attendees already attending the event.
     * @param AttendeeList: List of IDs of Attendees we need to add.
     */
    public void AddAttendees(List<Integer> AttendeeList) {
        eventAttendees.addAll(AttendeeList);
    }

}