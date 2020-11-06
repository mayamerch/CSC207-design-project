package User;

import java.util.ArrayList;

public class Attendee extends User{

    /**
     * Constructs a new Attendee object
     * @param username : a name unique to this instance of user, is a String
     * @param password : a password used to log in, is a String
     */
    private ArrayList<String> friends_list;

    public Attendee(String username, String password) {
        super(username, password);
        this.friends_list = new ArrayList<>();
    }
    /**
     * Gets and returns the Attendee's friends list
     * @return Attendee's friends list as ArrayList<String></String>
     */
    public ArrayList<String> getFriends_list() {
        return friends_list;
    }
    /**
     * Adds a User's username to this Attendee's list of friends
     * @param friend_username: the username of the new friend
     */
    public void add_friend(String friend_username){
        this.friends_list.add(friend_username);
    }
    /**
     * Remove's a User's username from this Attendee's list of friends
     * @param friend_username: the username of the friend
     * TODO: modify with user_Id if necessary
     */
    public void remove_friend(String friend_username){
        this.friends_list.remove(friend_username);
        // remove's first instance of friend's username. can replace with ID if we allow
        // Duplicate usernames. Hypothetical user may want friends list of names not IDs
        // Can we store a list of ID's and represent them onscreen as usernames?
        // If using IDs, we can remove(object) safely.
    }
}
