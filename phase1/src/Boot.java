import EventPackage.Event;
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
        User user1 = new Attendee("ritvik", "hello");
        User user2 = new Organiser("adam", "123");
        User user3 = new Speaker("edward", "uoft");
        Event e = EventRegistry.createEvent("talk1", new Date(), 45, user3.get_userID());
        Message message = new Message("this is a new message", user1.get_username());

        e.startConversation(new ArrayList<User>(Arrays.asList(user1,user2)));
        ArrayList<Conversation> a = e.getConversations(user1);
        System.out.println(a);
        //e.getConversations(user1).get(0).sendMessage(message, user1);
        //e.getConversations(user1).get(0).readMessages();
    }
}
