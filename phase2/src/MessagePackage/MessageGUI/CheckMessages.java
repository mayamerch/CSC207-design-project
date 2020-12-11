package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showAllMessages;
    private JPanel mainPanel;
    private JTextField enterID;
    private JButton showMessagesFromUserButton;
    private JButton backButton;

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

        showAllMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myChats = chatroomController.myChats(userID);
                messageArea.setText(myChats);
            }
        });

        showMessagesFromUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int senderID = Integer.parseInt(enterID.getText());
                String chatsFromUser = chatroomController.getChatsFromUser(userID, senderID);
                messageArea.setText(chatsFromUser);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendeeMessageMenu attendeeMessageMenu = new AttendeeMessageMenu(presenter);
                attendeeMessageMenu.setVisible(true);
                setVisible(false);
            }
        });

    }

}
