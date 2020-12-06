package GUI;

import UserPackage.UserController;
import EventPackage.EventOuterLayer.EventController;
import UserPackage.UserManager;
import UserPackage.UserPresenter;
import UserPackage.UserType;

import java.util.List;
// import MessagePackage.BroadcastController;
// import MessagePackage.ChatroomController;

public class Presenter {
    UserController userController;
    UserPresenter userPresenter;

    public Presenter(){
        this.userController = new UserController();
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }

    // for testing purposes
    public Presenter(UserManager userManager) {
        this.userController = new UserController(userManager);
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }

    public boolean userLogin(String username, String password){
        return userController.userLogin(username, password);
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

}