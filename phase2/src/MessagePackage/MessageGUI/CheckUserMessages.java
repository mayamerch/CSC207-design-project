package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;
import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CheckUserMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showMessages;
    private JPanel mainPanel;
    private JTextField enterID;
    private JButton viewFriends;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckUserMessages(Presenter presenter) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        ChatroomController chatroomController = presenter.getChatroomController();
        int userID = presenter.getUserController().getCurrentUserId();

        viewFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewFriendsForm viewFriendsForm = new ViewFriendsForm();
                viewFriendsForm.setContentPane(viewFriendsForm.getMainPanel());
                viewFriendsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewFriendsForm.pack();
                viewFriendsForm.setVisible(true);
                setVisible(true);
            }
        });

        showMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String userID = enterID.getText();
                //TODO: (done) get messages from userID and current user and display in textarea
                Presenter presenter = new Presenter();
                ChatroomController chatroomController = presenter.getChatroomController();
                int userID = presenter.getUserController().getCurrentUserId();
                messageArea.setText(chatroomController.myChats(userID));
            }
        });


    }

}
