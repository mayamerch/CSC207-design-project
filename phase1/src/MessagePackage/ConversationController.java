package MessagePackage;

import java.util.ArrayList;
import java.util.Iterator;

public class ConversationController {
    public ArrayList<Conversation> conversations;
    public Iterator iterator;

    /**
     * Creates an instance of ConversationSystem that contains all the recorded conversations (empty at first)
     */
    public ConversationController(ArrayList<Conversation> conversations){
        this.conversations = conversations;
        this.iterator = conversations.iterator();
    }

    public void createConversation(){

    }

    public void createBroadcast(){

    }

    public void startBroadcastInAllSpeakerEvents(){

    }

    public ArrayList<Conversation> returnConversationsforUserID(){
        return null;
    }

    public void iterate(){
        while(iterator.hasNext()){
            iterator.next();
        }
    }

}
