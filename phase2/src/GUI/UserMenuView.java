package GUI;

import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenuView extends JFrame{
    private JButton createAccountButton;
    private JButton manageFriendsButton;
    private JButton vipButton;
    private JPanel userMenuPanel;
    private JButton backButton;

    private Presenter presenter;


    public UserMenuView(Presenter presenter) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.presenter = presenter;
        this.setContentPane(userMenuPanel);
        this.pack();
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateAccountMenuView createAccountMenuView = new CreateAccountMenuView(presenter);
                createAccountMenuView.setVisible(true);
            }
        });
        manageFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                FriendRequestMenuView friendRequestMenuView = new FriendRequestMenuView(presenter);
                friendRequestMenuView.setVisible(true);
            }
        });
        vipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presenter.getUserType() == UserType.ORGANIZER){
                    setVisible(false);
                    VIPMenuView vipMenuView = new VIPMenuView(presenter);
                    vipMenuView.setVisible(true);}
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainMenuView mainMenuView = new MainMenuView(presenter);
                mainMenuView.setVisible(true);
            }
        });
    }
}
