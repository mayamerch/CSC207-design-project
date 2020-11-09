package User;

import EventPackage.Event;
import EventPackage.EventManager;
import MessagePackage.Conversation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Speaker extends User{
    /**
     * Constructs a new Speaker object with an empty list of talks to attend
     *
     * @param username : a name unique to this instance of user, is a String
     * @param password : a password used to log in, is a String
     */
    ArrayList<Integer> talks_list;
    ArrayList<Conversation> conversations;
    EventManager eventManager = new EventManager();

    public Speaker(String username, String password) {
        super(username, password);
        this.talks_list = new ArrayList<>();
        this.conversations = new ArrayList<Conversation>();
    }

    /**
     * Gets and returns the Speaker's Talks list
     * @return Speaker's talks list as ArrayList<Integer>
     */
    public ArrayList<Integer> get_talks_list() {
        return talks_list;
    }

    /**
     * Adds an event to this Speaker's list of talks
     * @param eventID: the ID of the new event the speaker is to give
     */
    public void add_event(int eventID){
        this.talks_list.add(eventID);
    }

    /**
     * Removes an event to this Speaker's list of talks
     * @param eventID: the ID of the event the speaker is to give
     * TODO: modify with event_name if necessary
     */
    public void remove_event(int eventID){
        this.talks_list.remove(eventID);
    }

}
