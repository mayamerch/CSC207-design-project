package MessagePackage;

import User.User;

import java.util.ArrayList;

public class Conversation {

    ArrayList<User> userList;
    MessageQueue messageQueue;

    public Conversation(ArrayList<User> users) {
    }

    public void Conversation(ArrayList<User> userList){
        this.userList = userList;
        this.messageQueue = new MessageQueue();
    }

    public void sendMessage(Message message, User sender){
        messageQueue.pushMessage(message);
    }

    public ArrayList<Message> readMessages(){
        return messageQueue.getMessages();
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
