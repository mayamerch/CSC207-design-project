package MessagePackage;

import UserPackage.UserFactory;
import UserPackage.UserManager;

import java.util.ArrayList;

public class MessageQueue {

    ArrayList<Message> messageQueue;
    UserManager userManager;

    /**
     * Constructs a new MessageQueue object
     */
    public MessageQueue(){
        this.messageQueue = new ArrayList<Message>();
    }

    /**
     * Adds Message to messageQueue upon being sent
     */
    public void pushMessage(Message message){
        this.messageQueue.add(message);
    }

    /**
     * Returns all the messages as User has sent/received in chronological order
     * @return message history of a user
     */
    public ArrayList<Message> getMessages(){
        return this.messageQueue;
    }

    @Override
    public String toString() {
        String s = new String();
        for(Message m : messageQueue){
            s += userManager.getUserByID(m.userId).get_username() + " : " + m.content;
        }
        return s;
    }
}
