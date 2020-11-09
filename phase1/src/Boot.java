import EventPackage.Event;
import EventPackage.EventManager;
import EventPackage.EventRegistry;
import MessagePackage.Conversation;
import MessagePackage.Message;
import UserPackage.Attendee;
import UserPackage.User;
import UserPackage.Organiser;
import UserPackage.Speaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Boot {

    public static void main(String[] args){
        EventManager eventManager = new EventManager();

        User user1 = new Attendee("ritvik", "hello");
        User user4 = new Attendee("john cena", "world");

        User user2 = new Organiser("adam", "123");
        User user3 = new Speaker("edward", "uoft");
        Integer eventId = eventManager.createEvent("talk", 1, new Date(), user3.get_userID(), 1);
        Event e = eventManager.getEvent(eventId);

    }
}
