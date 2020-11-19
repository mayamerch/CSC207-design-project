package UserPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class UserManager implements Serializable {

    private UserFactory factory = new UserFactory();
    private LinkedList<User> userList;
    private int usersCreated;
    private LinkedList<User> speakerList;
    private LinkedList<User> attendeeList;
    private LinkedList<User> organiserList;


    /**
     * Creates a UserManager with an empty list of Users and a user factory, and a usersCreated of 0
     */
    public UserManager() {
        userList = new LinkedList<User>();
        usersCreated = 0;
        organiserList = new LinkedList<User>();
        attendeeList = new LinkedList<User>();
        speakerList = new LinkedList<User>();
    }

    public UserManager(LinkedList<User> list) {
        //use this for when taking in a user list from a file
        userList = list;
        usersCreated = findMaxIDFromList(list);
        remakeAOSLists(list);
    }

    /**
     * Creates a New user based on the type of User specified then adds it to a list of
     * Users and assigns the new user an ID
     * @param newUsername: The username of the new account, String
     * @param newPassword : The password of the new account, String
     * @param usertype: a character specifying the type of user to be created
     */
    public boolean createAccount(String newUsername, String newPassword, String usertype){
        User new_user;
        new_user = this.factory.getuser(newUsername, newPassword, usertype);
        // Casting as User?
        if (new_user != null &&checkUnusedUsername(newUsername)) {
            userList.add(new_user);
            int new_userID = usersCreated + 1;
            new_user.setUserID(new_userID);
            if (new_user.getType() == 'S'){
                speakerList.add(new_user);
            }
            else if (new_user.getType() == 'O') {
                organiserList.add(new_user);
            }
            else {
                attendeeList.add(new_user);
            }
            usersCreated += 1;
            return true;
        }
        return false;
    }
    private boolean checkUnusedUsername(String username){
        for (User x: userList){
            if (x.getUsername().equals(username))
                return false;
        }
        return true;
    }

    /**
     * Takes an instance of User provided by the controller and a possible
     * username and password combination and check to see if they match
     * the username and password stored in the instance of user
     * @param username: The String entered as username of the account,
     * @param password : The String entered as the password of the account,
     * @param user: an instance of User the person is trying to log into
     */
    public boolean validateLogin(User user, String username, String password){
        return ((user.getUsername().equalsIgnoreCase(username)) && (user.getPassword().equals(password)));
    }
    public int validateLogin(String username, String password){
        for (User x : userList){
            if (validateLogin(x, username, password))
                return x.getUserID();
        }
        return -1;
    }

    public  LinkedList<User> getUserList(){
        return userList;
    }

    public LinkedList<User> getSpeakerList() {return speakerList;}


    /**
     * TODO: Change this to linked list implementation. Can replace validate ID with this function`
     */
    public User getUserByID(int userID) {
        for (User user : userList) {
            if (userID == user.getUserID()) {
                return user;
            }
        }
        return null;
    }

    /**
     * TODO: MAy need to create separate lists for Attendee, Organiser and Speaker
     * AttendeeManager and OrganiserManager? Not very expandable
     */
    public boolean addFriend(int userID, int friendID){
        User currentUser, friend;
        currentUser = getUserByID(userID);
        friend = getUserByID(friendID);

        int currentUserFriendListSizeBeforeAdding = currentUser.getFriendsList().size();

        currentUser.addFriend(friend.getUserID());
        friend.addFriend(currentUser.getUserID());

        //returns true if currentUser has successfully added someone to friendsList
        return currentUser.getFriendsList().size() > currentUserFriendListSizeBeforeAdding;

    }
    public boolean sendFriendRequest(int userID, int friendID){
        User friend;
        friend = getUserByID(friendID);
        User currentUser = getUserByID(userID);

        // Check if friend already in currentUser's friends List
        for (int x: currentUser.getFriendsList()) {
            if (x == friendID) {
                return false;
            }
        }
        // check if currentUser already has sent a friend request to potential friend
        for (int x: friend.getFriendRequestList()){
            if (x == userID){
                return false;
            }
        }
        friend.addFriendRequest(userID);
        return true;
    }
    public boolean acceptFriendRequest(int userID, int friendID){
        User currentUser = getUserByID(userID);
        User friend = getUserByID(friendID);

        for (int x: currentUser.getFriendRequestList()){
            if (x == friendID){
                addFriend(userID, friendID);
                currentUser.removeFriendRequest(friendID);
                return true;
            }
        }
        return false;
        // This means the friend is not in the friend request list
    }


    /**
     * Returns a list of usernames
     * @return a list of usernames of users in UserManager
     */
    public ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for (User user: userList)
            usernames.add(user.getUsername());
        return usernames;
    }

    public LinkedList<User> getOrganiserList(){
        return this.organiserList;
    }

    private int findMaxIDFromList(LinkedList<User> list){
        int maxID = 0;
        if (list == null)
            return 0;
        for(User x: list){
            if (x.getUserID() > maxID)
                maxID = x.getUserID();
        }
        return maxID;
    }
    private void remakeAOSLists(LinkedList<User> list){
        //remakes each type of list from a userList
        for (User x: list){
            switch (x.getType()){
                case 'A':
                    attendeeList.add(x);
                    break;
                case 'O':
                    organiserList.add(x);
                    break;
                case 'S':
                    speakerList.add(x);
                    break;
            }
        }
    }
}
