package MessagePackage;

import User.User;

import java.util.ArrayList;

public class Conversation {

    ArrayList<User> userList;
    MessageQueue messageQueue;
    enum status {ACCEPTED, PENDING, REJECTED}
    status myStatus;

    public Conversation(ArrayList<User> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    public Boolean sendMessage(Message message, User sender){
        if(this.myStatus == status.ACCEPTED){
            this.messageQueue.pushMessage(message);
            return true;
        }
        return false;
    }

    public void acceptConversation(){
        this.myStatus = status.ACCEPTED;
    }

    public void rejectConversation(){
        this.myStatus = status.REJECTED;
    }

    public ArrayList<Message> readMessages(){
        return this.messageQueue.getMessages();
    }

    public Boolean isUserParticipating(User user){
        for(User user1 : this.userList){
            if(user1.get_userID()==user.get_userID()){
                return true;
            }
        }
        return false;
    }
}
