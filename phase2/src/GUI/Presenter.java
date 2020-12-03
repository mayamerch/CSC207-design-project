package GUI;

import UserPackage.UserController;
import EventPackage.EventOuterLayer.EventController;
import UserPackage.UserManager;
import UserPackage.UserPresenter;

import java.util.List;
// import MessagePackage.BroadcastController;
// import MessagePackage.ChatroomController;

public class Presenter {
    UserController userController;
    UserPresenter userPresenter;

    public Presenter(){
        this.userController = null;
        this.userPresenter = null;
    }

    // for testing purposes
    public void setUserController(UserManager userManager) {
        this.userController = new UserController(userManager);
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }
    public void setUserController() {
        this.userController = new UserController();
        this.userPresenter = new UserPresenter(userController.getUserManager());
    }

    public boolean userLogin(String username, String password){
        return userController.userLogin(username, password);
    }
    public boolean sendFriendRequest(String username){
        return userController.sendFriendRequest(username);
    }
    public boolean acceptFriendRequest(String username){
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

}
