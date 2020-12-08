package GUI;

import EventPackage.EventOuterLayer.EventPresenter;
import MessagePackage.BroadcastController;
import MessagePackage.ChatroomController;
import MessagePackage.ConversationPresenter;
import UserPackage.UserController;
import EventPackage.EventOuterLayer.EventController;
import UserPackage.UserManager;
import UserPackage.UserPresenter;
import UserPackage.UserType;

import java.util.List;
// import MessagePackage.BroadcastController;
// import MessagePackage.ChatroomController;

public class Presenter {
    private UserController userController;
    private UserPresenter userPresenter;
    private EventController eventController;
    private EventPresenter eventPresenter;
    private BroadcastController broadcastController;
    private ChatroomController chatroomController;
    private ConversationPresenter conversationPresenter;


    public Presenter(){
        this.userController = new UserController();
        this.userPresenter = new UserPresenter(userController.getUserManager());
        this.broadcastController = new BroadcastController(eventController.getEventManager(), userController.getUserManager());
        this.chatroomController = new ChatroomController(eventController.getEventManager(), userController.getUserManager());
        this.conversationPresenter = new ConversationPresenter();
    }

    // for testing purposes
    public Presenter(UserManager userManager) {
        this.userController = new UserController(userManager);
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }
    public UserController getUserController(){return this.userController;}
    public EventPresenter getEventPresenter(){return this.eventPresenter;}

    public boolean userLogin(String username, String password){
        boolean login = userController.userLogin(username, password);
        if (login){
            instantiateEventControllerAndPresenter(userController.getCurrentUserId());
            return true;
        }
        return false;
    }
    private void instantiateEventControllerAndPresenter(int userID){
        this.eventController = new EventController(userID, userController.checkUserVIP(userID),
                userController.getSpeakerIds());
        this.eventPresenter = new EventPresenter(eventController);
    }
    public void userLogOut(){ userController.logOut();}
    public boolean sendFriendRequest(String username){
        // chooses whether to add by ID or Username
        if (Character.isDigit(username.charAt(0))){
            // ID has been entered
            int userID = Integer.parseInt(username);
            return userController.sendFriendRequest(userID);
        }
        return userController.sendFriendRequest(username);
    }
    public boolean acceptFriendRequest(String username){
        if (Character.isDigit(username.charAt(0))){
            // ID has been entered
            int userID = Integer.parseInt(username);
            return userController.acceptFriendRequest(userID);
        }
        return userController.acceptFriendRequest(username);
    }
    public List<Integer> getFriendsList(){
        return userController.getFriendsList();
    }
    public List<Integer> getFriendRequestList(){
        return userController.getFriendRequestList();
    }
    public List<String> displayFriendRequestList(){
        List<Integer> friendRequestList = getFriendRequestList();
        return userPresenter.userIDListToString(friendRequestList);
    }
    public List<String> displayFriendList(){
        List<Integer> friendList = getFriendsList();
        return userPresenter.userIDListToString(friendList);
    }
    public boolean checkNotLoggedIn(){
        return userController.validateNotLoggedIn();
    }
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
        public boolean changeVIP(String username, boolean newVIPStatus){
            if (Character.isDigit(username.charAt(0))){
                int userID = Integer.parseInt(username);
                return userController.changeUserVIP(userID, newVIPStatus);}
            return userController.changeUserVIP(username, newVIPStatus);
        }

        // message package
        public boolean displayMessages(){
            return true;
        }

        public boolean sendMessages(){
            return true;
        }

        public boolean sendBroadcasts(){
            return true;
        }


}
