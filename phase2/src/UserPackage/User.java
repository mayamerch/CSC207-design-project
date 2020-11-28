package UserPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int userID;
    private String username;
    private String password;
    private char type;
    private ArrayList<Integer> friendsList;
    private ArrayList<Integer> friendRequestList;

    /**
     * Constructs a new User object
     * @param username: a name unique to this instance of user, is a String
     * @param password: a password used to log in, is a String
     */
    public User(String username, String password, char type) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.friendsList = new ArrayList<>();
        this.friendRequestList = new ArrayList<>();
    }

    /**
     * Sets the username of this User.
     * @param new_username: a new username String
     * TODO: remove this method and place it in Controller/Usermanager if unique usernames
     */
    public void set_username(String new_username) {
        this.username = new_username;
    }


    /**
     * Sets the password of this User.
     * @param new_password: a new password String
     */
    public void set_password(String new_password) {
        this.password = new_password;
    }
    /**
     * Returns the username of this User.
     * @return username
     */
    public String getUsername(){
        return username;
    }
    /**
     * Returns the password of this User.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the userID of this User.
     * @return userID, an integer
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Sets the UserID of this User.
     * @param new_userID: a new password String
     */
    public void setUserID(int new_userID) {
        this.userID = new_userID;
    }
    /**
     * Gets the Type of this User.
     * @return Type of the User, a character
     */
    public char getType(){return type;}

    /**
     * Sets the Type of this User.
     * @param type of the User, a character
     */
    public void setType(char type){
        this.type = type;
    }

    public ArrayList<Integer> getFriendsList() {
        return friendsList;
    }

    public ArrayList<Integer> getFriendRequestList(){return friendRequestList;}

    /**
     * Adds a User's username to this Attendee's list of friends
     * @param friendID: the id of the new friend
     */
    public void addFriend(int friendID){

        this.friendsList.add(friendID);
    }

    /**
     * Adds a User's username to this User's list of friend requests
     * @param friendID: the ID of the new friend
     */
    public void addFriendRequest(int friendID){
        this.friendRequestList.add(friendID);
    }
    /**
     * Removes a User's username to this User's list of friend requests
     * @param friendID: the ID of the new friend
     */
    public void removeFriendRequest(int friendID){
        this.friendRequestList.remove(Integer.valueOf(friendID));
    }

    /**
     * Remove's a User's username from this Attendee's list of friends
     * @param friendID: the id of the friend's account
     * TODO: modify with user_Id if necessary
     */
    public void remove_friend(int friendID){
        this.friendsList.remove(Integer.valueOf(friendID));
    }


}


