package UserPackage;

import java.util.ArrayList;

public class Speaker extends User{
    /**
     * Constructs a new Speaker object with an empty list of talks to attend
     *
     * @param username : a name unique to this instance of user, is a String
     * @param password : a password used to log in, is a String
     */
    ArrayList<Integer> TalksList;
    public Speaker(String username, String password, char type) {
        super(username, password, type);
        this.TalksList = new ArrayList<>();
    }
    /**
     * Gets and returns the Speaker's Talks list
     * @return Speaker's talks list as ArrayList<Integer>
     */
    public ArrayList<Integer> getTalksList() {
        return TalksList;
    }
    /**
     * Adds an event to this Speaker's list of talks
     * @param eventID: the ID of the new event the speaker is to give
     */
    public void add_event(int eventID){
        this.TalksList.add(Integer.valueOf(eventID));
    }
    /**
     * Removes an event to this Speaker's list of talks
     * @param eventID: the ID of the event the speaker is to give
     * TODO: modify with event_name if necessary
     */
    public void remove_event(int eventID){
        this.TalksList.remove(Integer.valueOf(eventID));}
}
