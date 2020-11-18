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

        Speaker maya = new Speaker("maya", "password", 's');
        ArrayList<Integer> broadcasters = new ArrayList<>();
        broadcasters.add(419);

        Date date = new Date(2020, 04, 19);
        Event e = new Event(1, "maya's event", 419, date, 2858, 60);
        e.addAttendee(200);
        em.createEvent("maya's event", 2858, date, 419, 60);

        Broadcast b = new Broadcast(broadcasters, e);
        bc.sendBroadcast(419, e, "testing the presenter");

        ConversationPresenter conversationPresenter = new ConversationPresenter();
        conversationPresenter.run(cc, bc, em);
    }
}