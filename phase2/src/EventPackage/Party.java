package EventPackage;

import java.util.Date;

public class Party extends Event{

    public Party(int eventId, String eventName, int eventCapacity, Date eventDate, int eventRoom, int eventDuration,
                 boolean VIPStatus)
    {
        super(eventId, eventName, eventCapacity, eventDate, eventRoom, eventDuration, VIPStatus);
    }

}