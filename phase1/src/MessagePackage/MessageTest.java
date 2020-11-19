package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;
import UserPackage.UserController;
import UserPackage.UserManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class MessageTest {
    public static void main(String[] args) {
        ChatroomController cc = new ChatroomController();
        EventManager em = new EventManager();
        BroadcastController bc = new BroadcastController(em);
        UserManager um = new UserManager();
        UserController uc = new UserController(um);

        //um.createAccount("mm", "password", "ATTENDEE");
        //System.out.println(um.getUserList());

        //Speaker maya = new Speaker("maya", "password", 'S');
        ArrayList<Integer> broadcasters = new ArrayList<>();
        broadcasters.add(419);

        ArrayList<Integer> userlist = new ArrayList<>();
        userlist.add(200);

        Date date = new Date(2020, 04, 19);

        em.createEvent("maya's event", 2858, date, 419, 60); //id 1
        Event e = em.getEvent(1);
        e.addAttendee(200);

        bc.sendBroadcast(419, 1, "testing the presenter");

        MessageQueue mq = new MessageQueue();
        Chatroom c = new Chatroom(userlist, mq);
        c.sendMessage("my message", 419);
        cc.sendChat(userlist, 419, "my message");

        ConversationPresenter conversationPresenter = new ConversationPresenter();
        conversationPresenter.run(cc, bc);

    }
}
