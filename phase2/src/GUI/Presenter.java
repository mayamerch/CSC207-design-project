package GUI;

import EventPackage.EventOuterLayer.EventPresenter;
import EventPackage.EventOuterLayer.EventProgramPresenter;
import MessagePackage.BroadcastController;
import MessagePackage.ChatroomController;
import MessagePackage.ConversationPresenter;
import UserPackage.UserController;
import EventPackage.EventOuterLayer.EventController;
import UserPackage.UserManager;
import UserPackage.UserPresenter;
import UserPackage.UserType;

import java.util.ArrayList;
import java.util.List;
//<<<<<<<
//=======

//>>>>>>> origin/master

public class Presenter {
    private UserController userController;
    private UserPresenter userPresenter;
    private EventController eventController;
    private EventPresenter eventPresenter;
    private EventProgramPresenter programPresenter;
    private BroadcastController broadcastController;
    private ChatroomController chatroomController;
    private ConversationPresenter conversationPresenter;

    /**
     * Constructs a Presenter object which contains the other Controllers
     */
    public Presenter(){
        this.userController = new UserController();
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }

    // for testing purposes
    /**
     * Constructs a Presenter object which contains the other Controllers based off an existing User Manager
     * @param userManager the existing UserManager the presenter will use, UserManager
     */
    public Presenter(UserManager userManager) {
        this.userController = new UserController(userManager);
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }
    /**
     * Getter for the EventProgramPresenter attribute of the Presenter
     * @return the programPresenter attribute of the Presenter
     */
    public EventProgramPresenter getProgramPresenter(){
        return this.programPresenter;
    }

    public ChatroomController getChatroomController() {
        return this.chatroomController;
    }

    public BroadcastController getBroadcastController() {
        return this.broadcastController;
    }

    public ConversationPresenter getConversationPresenter() {
        return conversationPresenter;
    }

    /**
     * Getter for the UserController attribute of the Presenter
     * @return the UserController attribute of the Presenter
     */
    public UserController getUserController(){return this.userController;}
    /**
     * Getter for the EventController attribute of the Presenter
     * @return  the EventController attribute of the Presenter
     */
    public EventPresenter getEventPresenter(){return this.eventPresenter;}

    /**
     * Attempts to log the User in based on a given Username and Password
     * @param username the proposed username of a User, String
     * @param password the proposed password of a User, String
     * @return true if the User was logged in
     */
    public boolean userLogin(String username, String password){
        boolean login = userController.userLogin(username, password);
        if (login){
            instantiateEventAndMessages(userController.getCurrentUserId());
            return true;
        }
        return false;
    }
    /**
     * Calls on User Controller to return type of user currently using controller
     * @return UserType of user who is logged in or Null if not logged in
     */
    public UserType getUserType(){
        if (checkNotLoggedIn()){return null;}
        return userController.getUserType();
    }
    /**
     * A helper method to instantiate the EventController and EventPresenter and Message Controllers Upon Login
     * @param userID, int
     */
    private void instantiateEventAndMessages(int userID){
        this.eventController = new EventController(userID, userController.checkUserVIP(userID),
                userController.getSpeakerIds());
        this.eventPresenter = new EventPresenter(eventController);
        this.programPresenter = new EventProgramPresenter(userController.getUserManager(),
                eventController.getEventManager(),userController.getCurrentUserId(),userController.getUserType());
        this.broadcastController = new BroadcastController(eventController.getEventManager(), userController.getUserManager());
        this.chatroomController = new ChatroomController(eventController.getEventManager(), userController.getUserManager());
        this.conversationPresenter = new ConversationPresenter();
    }
    /**
     * Logs the User out of the program
     */
    public void userLogOut(){ userController.logOut();}

    /**
     * Sends a Friend Request based on a String inputted by the User. If the first character of
     * the string is a digit, it will try to send a friend request based on ID. If not, it will
     * send a friend request based on Useername
     * @param userInput, the input of the User to send a Friend request, String
     * @return true or false based on if the friend request was successfully sent.
     */
    public boolean sendFriendRequest(String userInput){
        // chooses whether to add by ID or Username
        if (userInput.length() == 0){return false;}
        if (Character.isDigit(userInput.charAt(0))){
            // ID has been entered
            int userID = Integer.parseInt(userInput);
            return userController.sendFriendRequest(userID);
        }
        return userController.sendFriendRequest(userInput);
    }
    /**
     * Accepts a Friend Request based on a String inputted by the User. If the first character of
     * the string is a digit, it will try to accept a friend request based on ID. If not, it will
     * accept a friend request based on Username
     * @param userInput, the input of the User to send a Friend request, String
     * @return true or false based on if the friend request was successfully accepted and friend added.
     */
    public boolean acceptFriendRequest(String userInput){
        if (userInput.length() == 0) {return false;}
        if (Character.isDigit(userInput.charAt(0))){
            // ID has been entered
            int userID = Integer.parseInt(userInput);
            return userController.acceptFriendRequest(userID);
        }
        return userController.acceptFriendRequest(userInput);
    }
    /**
     * Returns the incoming friend requests list of the current User in a format with Usernames and ID in brackets
     * @return List of Strings containing the Usernames and IDs of the User's incoming friends requests.
     */
    public List<String> displayFriendRequestList(){
        List<Integer> friendRequestList = userController.getFriendRequestList();
        return userPresenter.userIDListToString(friendRequestList);
    }
    /**
     * Returns the friends list of the current User in a format with Usernames and ID in brackets
     * @return List of Strings containing the Usernames and IDs of the User's friends.
     */
    public List<String> displayFriendList(){
        List<Integer> friendList = userController.getFriendsList();
        return userPresenter.userIDListToString(friendList);
    }
    public List<String> displayNotFriendsOrRequests(){
        List<Integer> excludeList = userController.getNotFriendsNotRequests();
        return userPresenter.userIDListToString(excludeList);
    }
    /**
     * Checks if the User is logged in with a valid account
     * @return true or false if the User is logged in with a valid account.
     */
    public boolean checkNotLoggedIn(){
        return userController.validateNotLoggedIn();
    }
    /**
     * Creates a new User Object with the right input of Username. returns true or false based on if
     * The User was successfully created or not. Uses UserController
     * @param username the username of the new User, String
     * @param password the pasword of the new user, String
     * @param userType the type of the new User, String
     * @return  True if the user was successfully created, false if it was not created
     */
    public boolean createAccount( String username, String password, String userType){
        if (userType.equals("Attendee")){
            return userController.createUser(username, password, UserType.ATTENDEE);
        }
        // if not, must be logged in
        if (checkNotLoggedIn()){
            return false;
        }
        // Check for correct type to login in Controller
        switch(userType){
            case "Organizer":
                return userController.createUser(username, password, UserType.ORGANIZER);
            case"Speaker":
                return userController.createUser(username, password, UserType.SPEAKER);
        }
        return false;}

    /**
     * Takes in an UserID and changes the VIP status based on User Input. If the first character
     * of the userInput String is a number, it will treat the input as a UserID. Otherwise, it
     * will treat it as a Username. It will change the VIP status of the User to newVIPStatus
     * @param username: ID of the user we want to change status of, int
     * @param newVIPStatus the new VIP status, boolean
     * @return true if the VIP status was changed, false if the status was already newVIPStatus
     */
    public boolean changeVIP(String username, boolean newVIPStatus){
        if (Character.isDigit(username.charAt(0))){
            int userID = Integer.parseInt(username);
            return userController.changeUserVIP(userID, newVIPStatus);}
        return userController.changeUserVIP(username, newVIPStatus);
    }
    /**
     * Saves the Map of Users in the UserManager within the UserController
     */
    public void saveUserMap(){
        userController.saveUserMap();
    }

    /**
     * Takes in an UserID and prints all messages for that User
     * @param userID the ID of the user trying to view their messages
     */
    public void displayMessages(int userID){
        System.out.println(chatroomController.myChats(userID));
    }

    public void sendMessages(ArrayList<Integer> recipients, int userID, String message){
        //return chatroomController.sendChat(recipients, userID, message);
    }

    public void sendBroadcasts(int userID){
        System.out.println(broadcastController.myBroadcasts(userID));
    }


}
