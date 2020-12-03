package GUI;

import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame{
    private JPanel mainMenuPanel;
    private JButton logoutButton;
    private JButton somethingSomethingButton;
    private JButton eventsButton;
    private JButton messagesButton;
    private JLabel loggedInAsLabel;
    private JLabel usernameIDLabel;
    private JButton exitButton;

    private UserController userController;

    public MainMenuView(UserController userController) {
        //this code is super incomplete
        //need something that keeps track of decisions made in the menu
        //so that the "super controller" knows where to go next
        // ie a listener, possibly use observer design pattern
        //also, we don't need to pass a user controller, feel free to
        //to remove userController stuff
        super();
        this.userController = userController;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.logOut();
            }
        });
    }
}
