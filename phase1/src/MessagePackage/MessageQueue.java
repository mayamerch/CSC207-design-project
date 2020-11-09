package MessagePackage;

import java.util.ArrayList;

public class MessageQueue {

    ArrayList<Message> messageQueue;

    public MessageQueue(){
        this.messageQueue = new ArrayList<Message>();
    }

    public void pushMessage(Message message){
        this.messageQueue.add(message);
    }

    public ArrayList<Message> getMessages(){
        return this.messageQueue;
    }
}
