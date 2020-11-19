package MessagePackage;

import java.util.ArrayList;

public class MessageQueue {

    ArrayList<Message> messageQueue;

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


    /**
     * To use in Gateway class for saving MessageQueue as a string to write to file.
     * @return string of form [message \t ...]
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for(Message m : messageQueue){
            s.append(m.toString()).append("\t");
        }
        s.append("]");
        return s.toString();
    }

    /**
     * Used in Presenter for displaying string in console
     * @return string of form message \n
     */
    public String format(){
        StringBuilder s = new StringBuilder();
        for(Message m : messageQueue){
            s.append(m.format());
        }
        return s.toString();
    }
}
