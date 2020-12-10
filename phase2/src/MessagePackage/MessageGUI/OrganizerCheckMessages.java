package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;

import javax.swing.*;

public class OrganizerCheckMessages extends JFrame{
    private JTextArea messageArea;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerCheckMessages(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getMainPanel());
        this.pack();
        Presenter presenter = new Presenter();
        ChatroomController chatroomController = presenter.getChatroomController();
        int userID = presenter.getUserController().getCurrentUserId();
        messageArea.setText(chatroomController.myChats(userID));
    }
}
