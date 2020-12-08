package GUI;

import sun.applet.Main;

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

    private Presenter presenter;
    public int choice;

    public FriendRequestMenuView(Presenter presenter){
        super();
        this.presenter = presenter;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(friendRequestPanel);
        this.pack();

        seeFriendsListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkLoggedIn()){
                displayUserList(presenter.displayFriendList());}
                else{FriendLabel.setText("Need to be logged in");}
            }
        });
        seeFriendRequestListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkLoggedIn()){
                displayUserList(presenter.displayFriendRequestList());}
                else{FriendLabel.setText("Need to be logged in");}
            }
        });
        acceptFriendRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for login first, on left side of if statement
                if (checkLoggedIn() && acceptFriendRequest()){
                    FriendLabel.setText("You have now added each other as friends");
                }
                else{FriendLabel.setText("This person has not sent you a request");}
            }
        });
        sendFriendRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkLoggedIn() && sendFriendRequest()){
                    FriendLabel.setText("Friend Request Sent");
                }
                else{FriendLabel.setText("Invalid Username");}
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

    public void displayUserList(List<String> userList){
        DefaultListModel<String> friendDisplay = new DefaultListModel();
        for (String x: userList){
            friendDisplay.addElement(x);
        }
        list1.setModel(friendDisplay);
    }
    public boolean sendFriendRequest(){
        String username = userNameTextField.getText();
        return presenter.sendFriendRequest(username);
    }
    public boolean acceptFriendRequest(){
        String username = userNameTextField.getText();
        return presenter.acceptFriendRequest(username);
    }
    public boolean checkLoggedIn(){
        return !presenter.checkNotLoggedIn();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
