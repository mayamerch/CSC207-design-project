package MessagePackage.MessageGUI;

import GUI.Presenter;
import UserPackage.UserController;

import javax.swing.*;
import java.util.List;

public class AttendeeCheckMessages extends JFrame{
    private JTextArea messageArea;
    private JButton showMessages;
    private JPanel mainPanel;
    private JTextField enterID;
    private JButton viewFriends;

    public AttendeeCheckMessages(UserController loggedInUserContoller) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }



}
