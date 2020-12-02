package GUI;

import UserPackage.User;
import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendMenuForm {
    private JButton refreshButton;
    private JList list1;
    private JButton back;
    private UserController userController;

    public FriendMenuForm(UserController userController) {
        User currentUser = userController.getUserManager().getUserByID(userController.getCurrentUserId());
        List currentFriendList = currentUser.getFriendsList();
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel friendDisplay = new DefaultListModel();
                for (Object x: currentFriendList){
                    friendDisplay.addElement(x);
                }
                list1.setModel(friendDisplay);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
