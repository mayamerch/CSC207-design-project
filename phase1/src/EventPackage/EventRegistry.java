package EventPackage;
import MessagePackage.Conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class EventRegistry {

    private static final ArrayList<Event> eventList = new ArrayList<Event>();
    private static Integer eventIdGenerator = 0;

    public static Event createEvent(String eventName, Date eventDate, Integer eventRoom, Integer eventSpeaker){
        Event e = new Event(EventRegistry.eventIdGenerator++, eventName, eventSpeaker, eventDate, eventRoom);
        eventList.add(e);
        return e;
    }

    public static ArrayList<Event> getEvents(){
        return EventRegistry.eventList;
    }
}
