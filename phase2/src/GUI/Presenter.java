package GUI;
import UserPackage.UserController;

public class Presenter {

    public static void main(String[] args) {
        UserController userController = new UserController();
        LoginView loginView = new LoginView(userController);
        while (!userController.validateNotLoggedIn()) {
            loginView.setVisible(true);
        }
        loginView.setVisible(false);
        loginView.dispose();
        MainMenuView mainMenuView = new MainMenuView(userController);
        mainMenuView.setVisible(true);
    }
}
