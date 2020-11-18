package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;

import java.util.ArrayList;
import java.util.Date;

public class MessageTest {
    public static void main(String[] args) {
        ChatroomController cc = new ChatroomController();
        BroadcastController bc = new BroadcastController();
        EventManager em = new EventManager();

        ConversationPresenter cp = new ConversationPresenter();

        Speaker maya = new Speaker("maya", "password", 's');
        Date date = new Date(2020, 04, 19);
        Event e = new Event(1, "maya's event", 419, date, 2858, 60);
        e.addAttendee(200);


        ArrayList<Integer> broadcasters = new ArrayList<>();
        broadcasters.add(419);

        Broadcast b = new Broadcast(broadcasters, e);
        bc.sendBroadcast(419, e, "testing the presenter");

        System.out.println(e.getEventAttendees());

        System.out.println(b.canRead(200));

        cp.run(cc, bc, em);
    }
}
