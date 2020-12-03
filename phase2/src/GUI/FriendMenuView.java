package GUI;

import UserPackage.User;
import UserPackage.UserController;
import UserPackage.UserPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendMenuView extends JFrame {
    private JButton refreshButton;
    private JList list1;
    private JButton back;
    private JPanel friendMenuPanel;
    private UserController userController;

    public FriendMenuView(){}
    public FriendMenuView(UserController userController) {
        super();
        this.userController = userController;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(friendMenuPanel);
        this.pack();

        List<Integer> currentFriendList = userController.getFriendsList();
        UserPresenter userPresenter = new UserPresenter(userController.getUserManager());
        List<String> formattedFriendList = userPresenter.userIDListToString(currentFriendList);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> friendDisplay = new DefaultListModel();
                for (String x: formattedFriendList){
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
