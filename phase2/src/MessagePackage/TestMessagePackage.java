package MessagePackage;

import EventPackage.EventEntities.Event;
import EventPackage.EventUseCases.EventManager;
import UserPackage.UserManager;
import UserPackage.UserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*

************************************************************
Tester for the Message Package (can be ignored for Phase 2)
************************************************************


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

        um.createAccount("user1", "password", UserType.SPEAKER); // id 1
        um.createAccount("user2", "password", UserType.ATTENDEE); // id 2
        um.createAccount("user3", "password", UserType.ORGANIZER);
        Date date = new Date(2020, 04, 19);
        em.createSingleSpeakerEvent("maya's event", 100, date, 1, 60, false, 1);
        Event e = em.getEvent(1);
        e.addAttendee(2);

        ArrayList<Integer> userList = new ArrayList<>();
        userList.add(1);
        userList.add(2);
        cc.sendChat(userList, 1, "user1 to user2");
        bc.sendBroadcastToEvent(3, 1, "testing the presenter");
        int currID = 1;

        //ConversationPresenter conversationPresenter = new ConversationPresenter();
        //conversationPresenter.run(currID, cc, bc);
        cc.saveChats();
        bc.saveBroadcasts();
    }
}
