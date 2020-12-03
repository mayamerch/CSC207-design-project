package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserManager;
import UserPackage.UserType;

import javax.swing.*;
import java.util.HashMap;

public class LoginTest {
    public static void main(String[] args){

        HashMap<Integer, User> userMap = new HashMap<>();
        UserManager userManager = new UserManager();

        userManager.createAccount("user1", "passy", UserType.ATTENDEE);
        userManager.createAccount("user2", "letmeinpls", UserType.ORGANIZER);

        UserController userController = new UserController(userManager);
        JFrame frame = new LoginView(userController);
        frame.setVisible(true);
    }
}
