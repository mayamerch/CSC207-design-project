package GUI;
import MessagePackage.MessageGUI.AttendeeCheckMessages;
import MessagePackage.MessageGUI.AttendeeMessageMenu;
import UserPackage.UserController;
import UserPackage.UserManager;
import UserPackage.UserType;

public class RunPresenter {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "user2", UserType.SPEAKER);
        userManager.createAccount("user3", "user3", UserType.SPEAKER);
        userManager.createAccount("user4", "user4", UserType.ATTENDEE);
        userManager.createAccount("user5", "user5", UserType.ATTENDEE);
        UserController userController = new UserController(userManager);
        userController.userLogin("user1", "user1");
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
        //new FriendMenuView(userController);
        // making a bunch of users to test scroll on friend request
        for (int i=6; i< 30; i++){
            userManager.createAccount("user" + i, "user"+i, UserType.ATTENDEE);
            userManager.sendFriendRequest(i, 1);
        }
        Presenter presenter = new Presenter(userController.getUserManager());
        // make a new user controller using the user manager from the old userController


        //LoginView loginView = new LoginView(presenter);
        //loginView.setVisible(true);
        AttendeeCheckMessages attendeeCheckMessages = new AttendeeCheckMessages(presenter.getUserController());
        attendeeCheckMessages.setVisible(true);

//        while (userController.validateNotLoggedIn()) {
//            loginView.setVisible(true);
//        }
//        loginView.setVisible(false);
//        loginView.dispose();
//        MainMenuView mainMenuView = new MainMenuView(presenter);
//        mainMenuView.setVisible(true);
    }
}
