package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.ChatroomController;

import javax.swing.*;

public class MessageWindow extends JFrame{
    private JTextArea messageArea;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MessageWindow(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        ChatroomController chatroomController = presenter.getChatroomController();
        int userID = presenter.getUserController().getCurrentUserId();
        //super();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setContentPane(getMainPanel());
        //this.pack();
        messageArea.setText(chatroomController.myChats(userID));
    }
}
