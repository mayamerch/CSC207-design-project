package UserPackage;

import MessagePackage.Conversation;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class User {
    private int userID;
    private String username;
    private String password;
    public ArrayList<Conversation> conversations;

    /**
     * Constructs a new User object
     * @param username: a name unique to this instance of user, is a String
     * @param password: a password used to log in, is a String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
        this.username = new_username;}

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

}


