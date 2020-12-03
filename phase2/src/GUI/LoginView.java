package GUI;

import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JButton enterButton;

    private UserController userController;

    public LoginView(UserController userController) {
        super();
        this.userController = userController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loggedIn;
                loggedIn = login();
                if (loggedIn)
                    loginLabel.setText("Logged in");
                else
                    loginLabel.setText("login failed");
            }
        });
    }

    //might not need this if the constructor works
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public boolean login(){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        return userController.userLogin(username, password);
    }
}
