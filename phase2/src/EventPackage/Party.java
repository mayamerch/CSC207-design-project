package EventPackage;

import java.util.Date;

public class Party extends Event{

    public Party(int eventId, String eventName, int eventCapacity, Date eventDate, int eventRoom, int eventDuration,
                 boolean VIPStatus)
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
        return "P";
    }
}