package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendMenuView extends JFrame {
    private JButton showFriendsButton;
    private JList list1;
    private JButton back;
    private JPanel friendMenuPanel;
    private JButton showFriendsRequestsButton;
    private UserController userController;

    public FriendMenuView(UserController userController) {
        super();
        this.userController = userController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(friendMenuPanel);
        this.pack();

        List<Integer> currentFriendList = userController.getFriendsList();
        List<Integer> currentFriendRequestList = userController.getFriendRequestList();

        UserPresenter userPresenter = new UserPresenter(userController.getUserManager());
        List<String> formattedFriendList = userPresenter.userIDListToString(currentFriendList);
        List<String> formattedFriendRequestList = userPresenter.userIDListToString(currentFriendRequestList);

        showFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUsers(formattedFriendList);
                /*DefaultListModel<String> friendDisplay = new DefaultListModel();
                for (String x: formattedFriendList){
                    friendDisplay.addElement(x);
                }
                list1.setModel(friendDisplay);*/
            }
        });
        showFriendsRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUsers(formattedFriendRequestList);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void displayUsers(List<String> list){
        DefaultListModel<String> userDisplay = new DefaultListModel();
        for (String x: list){
            userDisplay.addElement(x);
        }
        list1.setModel(userDisplay);
    }
}
