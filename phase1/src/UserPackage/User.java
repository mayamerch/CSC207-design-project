package UserPackage;

import MessagePackage.Conversation;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class User {
    private int userID;
    private String username;
    private String password;
    private ArrayList<Integer> friendIds;
    private ArrayList<Integer> friendRequests;
    private char type;

    /**
     * Constructs a new User object
     * @param username: a name unique to this instance of user, is a String
     * @param password: a password used to log in, is a String
     */
    public User(String username, String password, char type) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.friendIds = new ArrayList<>();
    }

    // user_ID needs to be unique so it needs to be assigned to user by something above like a use case
    // which can access all the usernames. Since use cases can edit entity info they can assign
    // attributes to entities
    /**
     * Sets the username of this User.
     * @param new_username: a new username String
     * TODO: remove this method and place it in Controller/Usermanager if unique usernames
     */
    public void set_username(String new_username) {
        this.username = new_username;
    }


    /**
     * Adds friends if they aren't already there
     * @param friendId Id of friend to be added
     * @return true if added, false if not added due to already being friends
     */
    public boolean addFriend(int friendId) {
        if (friendIds.contains(friendId))
            return false;
        friendIds.add(friendId);

        return true;
    }


    /**
     * remove a friend if he exists
     * @param friendId id of friend to be removed
     * @return true if removed, false if isn't a friend
     */
    public boolean removeFriend(int friendId) {
        if (friendIds.contains(friendId)) {
            friendIds.remove(friendId);
            return true;
        }
        return false;
    }

    /**
     * returns list of ids of friends
     * @return ids of friends
     */
    public ArrayList<Integer> getFriendIds() {
        return friendIds;
    }


    /**
     * adds a friend request if not already a friend or not requested
     * @param id id of friend requesting
     * @return true if added, false if not
     */
    public boolean addRequest(int id) {
        if (friendIds.contains(id) || friendRequests.contains(id)) {
            return false;
        }
        friendRequests.add(id);
        return true;
    }


    /**
     * remove a request if it exists
     * @param id id of friend, whose request needs to be removed
     * @return true if removed, false otherwise
     */
    public boolean removeRequest(int id) {
        if (friendRequests.contains(id)) {
            friendRequests.remove(id);
            return true;
        }
        return false;
    }

    /**
     * accepts a friend request if it exists
     * @param id id of friend to be accepted
     * @return true if accepted, false if not
     */
    public boolean acceptRequest(int id) {
        if (friendRequests.contains(id)) {
            friendRequests.remove(id);
            this.addFriend(id);
            return true;
        }
        return false;
    }


    /**
     * returns list of ids of users requesting to be friends
     * @return ids of users requesting
     */
    public ArrayList<Integer> getFriendRequests() {
        return friendIds;
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
    public String get_username(){
        return username;
    }
    /**
     * Returns the password of this User.
     * @return password
     */
    public String get_password() {
        return password;
    }

    /**
     * Returns the userID of this User.
     * @return userID, an integer
     */
    public int get_userID() {
        return userID;
    }
    /**
     * Sets the UserID of this User.
     * @param new_userID: a new password String
     */
    public void set_userId(int new_userID) {
        this.userID = new_userID;
    }

    public char getType(){return type;}

    public void setType(char type){
        this.type = type;
    }


}


