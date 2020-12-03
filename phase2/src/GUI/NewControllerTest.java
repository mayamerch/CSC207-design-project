package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserManager;
import UserPackage.UserType;

import javax.swing.*;
import java.util.HashMap;

public class NewControllerTest {
    public static void main(String[] args) {

        HashMap<Integer, User> userMap = new HashMap<>();
        UserManager userManager = new UserManager();

        userManager.createAccount("user1", "user1", UserType.ATTENDEE);
        userManager.createAccount("user2", "user2", UserType.ATTENDEE);
        userManager.createAccount("user3", "user3", UserType.ATTENDEE);
        userManager.createAccount("user4", "user4", UserType.ATTENDEE);
        userManager.createAccount("user5", "user5", UserType.ATTENDEE);


        UserController userController = new UserController(userManager);
        userController.userLogin("user1", "user1");
        // userController.createUser(); created user2, user2
        // userController.createUser(); created user3, user3
        userController.sendFriendRequest("user2");  // sent request to user2
        userController.sendFriendRequest("user3"); //
        userController.logOut();
        userController.userLogin("user2", "user2");
        userController.acceptFriendRequest("user1"); // accept user 1 from user2
        userController.logOut();
        userController.userLogin("user3", "user3");
        userController.acceptFriendRequest("user1"); // accept user 1 from user3
        userController.logOut();
        userController.userLogin("user4", "user4");
        userController.sendFriendRequest("user1");
        userController.logOut();
        userController.userLogin("user1", "user1"); // log in as user1
        //new FriendMenuView(userController);
        // making a bunch of users to test scroll on friend request
        for (int i=6; i< 30; i++){
            userManager.createAccount("user" + i, "user"+i, UserType.ATTENDEE);
            userManager.sendFriendRequest(i, 1);
        }

        JFrame frame = new FriendRequestMenuView(userController);
        frame.setVisible(true);
    }
}
