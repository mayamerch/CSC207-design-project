package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendMenuForm {
    private JButton refreshButton;
    private JList list1;
    private JButton back;
    private UserController userController;

    public FriendMenuForm(){}
    public FriendMenuForm(UserController userController) {
        User currentUser = userController.getUserManager().getUserByID(userController.getCurrentUserId());
        List<Integer> currentFriendList = currentUser.getFriendsList();
        UserPresenter userPresenter = new UserPresenter(userController.getUserManager());
        List<String> goodFriendList = userPresenter.userIDListToString(currentFriendList);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> friendDisplay = new DefaultListModel();
                for (String x: goodFriendList){
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
