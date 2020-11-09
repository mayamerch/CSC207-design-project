package MessagePackage;

import User.User;

import java.util.ArrayList;

public class Chatroom {

    ArrayList<User> userList;
    MessageQueue messageQueue;
    enum status {ACCEPTED, PENDING, REJECTED}
    status myStatus;

    public Chatroom(ArrayList<User> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    /*public Boolean sendMessage(Message message, User sender){
        if(this.myStatus == status.ACCEPTED){
            this.messageQueue.pushMessage(message);
            return true;
        }
        return false;
    }*/

    public void sendMessage(Message message, User sender){
        this.messageQueue.pushMessage(message);
    }

    public void acceptChatroom(){
        this.myStatus = status.ACCEPTED;
    }

    public void rejectChatroom(){
        this.myStatus = status.REJECTED;
    }

    public ArrayList<Message> readMessages(){
        return this.messageQueue.getMessages();
    }


}
