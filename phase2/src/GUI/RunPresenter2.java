package GUI;

import UserPackage.UserManager;
import UserPackage.UserType;

public class RunPresenter2 {

    public static void main(String[] args) {
//        UserManager userManager = new UserManager();
//        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
//        Presenter presenter1 = new Presenter(userManager);
//        presenter1.saveUserMap();
        // Should save user1 to UserFile.ser
        Presenter presenter2 = new Presenter();
        LoginView loginView = new LoginView(presenter2);
        loginView.setVisible(true);
    }
}
