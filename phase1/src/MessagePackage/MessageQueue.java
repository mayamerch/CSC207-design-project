package MessagePackage;

import java.util.ArrayList;

public class MessageQueue {

    ArrayList<Message> messageQueue;

    public void pushMessage(Message message){
        messageQueue.add(message);
    }

    public ArrayList<Message> getMessages(){
        return this.messageQueue;
    }
}
