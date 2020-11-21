package MessagePackage;

import EventPackage.Event;
import EventPackage.EventManager;
import UserPackage.Speaker;
import UserPackage.UserController;
import UserPackage.UserManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
This is a test file for the Message Package!

Things that are created:
-a ChatroomController
-an EventManager
-a UserManager
-a BroadcastController
-a speaker "maya"
-an attendee "user2"
-an event "maya's event" that "maya" is speaking at and "user2" is attending
-a chat being sent from "maya" to "user2"
-a broadcast being sent to "maya's event"

-a conversationPresenter to run all this!
 */

public class TestMessagePackage {
    public static void main(String[] args) throws IOException {
        EventManager em = new EventManager();
        UserManager um = new UserManager();

        ChatroomController cc = new ChatroomController(em, um);
        BroadcastController bc = new BroadcastController(em, um);

        um.createAccount("user1", "password", "SPEAKER"); // id 1
        um.createAccount("user2", "password", "ATTENDEE"); // id 2
        um.createAccount("user3", "password", "ORGANIZER");
        Date date = new Date(2020, 04, 19);
        em.createEvent("maya's event", 1, date, 1, 60); // id 1
        Event e = em.getEvent(1);
        e.addAttendee(2);

        ArrayList<Integer> userList = new ArrayList<>();
        userList.add(1);
        userList.add(2);
        cc.sendChat(userList, 1, "user1 to user2");
        bc.sendBroadcast(3, 1, "testing the presenter");
        int currID = 1;

        ConversationPresenter conversationPresenter = new ConversationPresenter();
        //conversationPresenter.run(currID, cc, bc);
        cc.saveChats();
        bc.saveBroadcasts();
    }
}
