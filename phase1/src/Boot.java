import EventPackage.Event;
import EventPackage.EventManager;
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

        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        String s = new String();
        for (Integer i : list){
            s = s+i;
        }
        System.out.println(s);

    }
}
