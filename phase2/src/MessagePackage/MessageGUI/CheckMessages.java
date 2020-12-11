package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;
import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CheckMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showMessages;
    private JPanel mainPanel;
    private JTextField enterID;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckMessages(Presenter presenter) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        ChatroomController chatroomController = presenter.getChatroomController();
        int userID = presenter.getUserController().getCurrentUserId();

        showMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myChats = chatroomController.myChats(userID);
                messageArea.setText(myChats);
            }
        });


    }

}
