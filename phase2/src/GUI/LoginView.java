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
    private JButton createAccountButton;
    private Presenter presenter;


    /**
     * GUI Menu to Login or go to Create Account menu
     * @param presenter Presenter to be used in this view
     */
    public LoginView(Presenter presenter) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        this.presenter = presenter;

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loggedIn;
                loggedIn = login();
                if (loggedIn){
                    loginLabel.setText("Logged in");
                    setVisible(false);
                    MainMenuView mainMenuView = new MainMenuView(presenter);
                    mainMenuView.setVisible(true);

                }
                else
                    loginLabel.setText("login failed");
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateAccountMenuView createAccountMenuView = new CreateAccountMenuView(presenter);
                createAccountMenuView.setVisible(true);
            }
        });
    }

    //might not need this if the constructor works

    private boolean login(){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        return presenter.userLogin(username, password);
    }
}
