package UserPackage;

public class UserControllerTest {
    public static void main(String[] args) {

        // test creating a new user without making a new account
        UserController userController = new UserController();
        // userController.createUser();
        // Entered "user1, user1, Organizer/Press 1" to make an account. Has ID 1
        // Every user created here will be stored in userFile.ser automatically from create user
        // Once the userFile.ser has been created it will create a user controller based off of the .ser file
        // I then created "user2, user2, Attendee/2", this has ID 2

        userController.UserLogin();
        userController.sendFriendRequest();
        // type 2 for ID to send a friend request to user 2
        // type 1 and see if it rejects sending a friend request
        userController.logOut();
        // This should take a \n for the scanner prompt before entering username to log in again
        userController.UserLogin();
        // sign in as user2
        userController.acceptFriendRequest();
        // accept from user1

        // On re running this code, the user list and users after the friend requests is not saved.

    }
}
