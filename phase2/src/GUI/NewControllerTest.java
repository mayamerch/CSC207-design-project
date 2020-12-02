package GUI;

import UserPackage.UserController;

public class NewControllerTest {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.userLogin();
        // userController.createUser(); created user2, user2
        // userController.createUser(); created user3, user3
        userController.sendFriendRequest();  //
        userController.sendFriendRequest();
        userController.logOut();
        userController.userLogin();
        userController.acceptFriendRequest(); // accept user 1 from user2
        userController.logOut();
        userController.userLogin();
        userController.acceptFriendRequest(); // accept user 1 from user3
        userController.logOut();
        userController.userLogin(); // log in as user1
        new FriendMenuForm(userController);
    }
}
