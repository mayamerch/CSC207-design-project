package GUI;

import UserPackage.UserController;

public class NewControllerTest {
    public static void main(String[] args) {
        UserController userController = new UserController();
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
        userController.userLogin("user1", "user1"); // log in as user1
        new FriendMenuForm(userController);
    }
}
