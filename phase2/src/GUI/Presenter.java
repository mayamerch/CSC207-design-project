package GUI;
import UserPackage.UserController;
import UserPackage.UserManager;
import UserPackage.UserType;

public class Presenter {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        userManager.createAccount("user1", "user1", UserType.ATTENDEE);
        UserController userController = new UserController(userManager);

        LoginView loginView = new LoginView(userController);
        while (userController.validateNotLoggedIn()) {
            loginView.setVisible(true);
        }
        loginView.setVisible(false);
        loginView.dispose();
        MainMenuView mainMenuView = new MainMenuView(userController);
        mainMenuView.setVisible(true);
    }
}
