package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserManager;
import UserPackage.UserType;

import javax.swing.*;
import java.util.HashMap;

public class LoginViewTest {
    public static void main(String[] args){

        HashMap<Integer, User> userMap = new HashMap<>();
        UserManager userManager = new UserManager();

        userManager.createAccount("user1", "passy", UserType.ATTENDEE);
        userManager.createAccount("user2", "letmeinpls", UserType.ORGANIZER);

        // UserController userController = new UserController(userManager);
        Presenter presenter = new Presenter();
        presenter.setUserController(userManager);
        JFrame frame = new LoginView(presenter);
        frame.setVisible(true);
    }
}
