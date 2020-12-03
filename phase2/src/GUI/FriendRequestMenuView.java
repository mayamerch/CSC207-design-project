package GUI;

import UserPackage.UserController;
import UserPackage.UserPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendRequestMenuView extends JFrame{
    private JButton acceptFriendRequestButton;
    private JButton seeFriendRequestListButton;
    private JButton sendFriendRequestButton;
    private JList list1;
    private JTextField userNameTextField;
    private JButton backButton;
    private JPanel friendRequestPanel;
    private JButton seeFriendsListButton;
    private JLabel FriendLabel;
    private JScrollPane FriendListScroll;

    private UserPresenter userPresenter;
    private UserController userController;
    private List<Integer> friendRequestList;
    private List<Integer> friendList;

    public FriendRequestMenuView(UserController userController){
        super();
        this.userController = userController;
        this.userPresenter = new UserPresenter(userController.getUserManager());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(friendRequestPanel);
        this.pack();
        this.friendRequestList = userController.getFriendRequestList();
        this.friendList = userController.getFriendsList();
        seeFriendsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFriendList();
            }
        });
        seeFriendRequestListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayFriendRequestList();
            }
        });
        acceptFriendRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acceptFriendRequest()){
                    FriendLabel.setText("You have now added each other as friends");
                }
                else{FriendLabel.setText("This person has not sent you a request");}
            }
        });
        sendFriendRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sendFriendRequest()){
                    FriendLabel.setText("Friend Request Sent");
                }
                else{FriendLabel.setText("Invalid Username");}
            }
        });
    }

    public void displayFriendRequestList(){
        List<String> formattedFriendRequestList = userPresenter.userIDListToString(friendRequestList);
        displayUserList(formattedFriendRequestList);
    }
    public void displayFriendList(){
        List<String> formattedFriendList = userPresenter.userIDListToString(friendList);
        displayUserList(formattedFriendList);
    }
    public void displayUserList(List<String> userList){
        DefaultListModel<String> friendDisplay = new DefaultListModel();
        for (String x: userList){
            friendDisplay.addElement(x);
        }
        list1.setModel(friendDisplay);
    }
    public boolean sendFriendRequest(){
        String username = userNameTextField.getText();
        return userController.sendFriendRequest(username);
    }
    public boolean acceptFriendRequest(){
        String username = userNameTextField.getText();
        return userController.acceptFriendRequest(username);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
