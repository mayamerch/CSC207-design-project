package MessagePackage;

import java.util.ArrayList;

public class Chatroom implements Conversation{

    ArrayList<Integer> userList;
    MessageQueue messageQueue;
    enum status {ACCEPTED, PENDING, REJECTED}
    status myStatus;

    public Chatroom(ArrayList<Integer> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    @Override
    public void sendMessage(String messageStr, int senderUserID){
        Message newMessage = new Message(messageStr, senderUserID);
        this.messageQueue.pushMessage(newMessage);
    }

    @Override
    public ArrayList<Message> readMessages(){
        return this.messageQueue.getMessages();
    }

    @Override
    public ArrayList<Integer> getAllReaderIDs(){
        return this.userList;
    }

    @Override
    public ArrayList<Integer> getAllSenderIDs(){
        return this.userList;
    }

    public void acceptChatroom(){
        this.myStatus = status.ACCEPTED;
    }

    public void rejectChatroom(){
        this.myStatus = status.REJECTED;
    }

}
