import EventPackage.Event;
import EventPackage.EventManager;
import EventPackage.EventRegistry;
import MessagePackage.Conversation;
import MessagePackage.Message;
import User.Attendee;
import User.User;
import User.Organiser;
import User.Speaker;

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

        Message message1 = new Message("this is a new message", user1.get_username());
        Message message2 = new Message("this is another message", user4.get_username());

        user1.startConversation(user4);
        user1.getConversations().get(0).sendMessage(message1, user1);
        user4.getConversations().get(0).sendMessage(message2, user4);
        for(Message m : user1.getConversations().get(0).readMessages()){
            System.out.println("Sender: " + m.from + " Message: " + m.content);
        }
    }
}
