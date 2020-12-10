package MessagePackage.MessageGUI;

import EventPackage.EventGUI.EventsView;
import GUI.MainMenuView;
import GUI.Presenter;
import MessagePackage.Message;
import UserPackage.User;
import UserPackage.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeMessageMenu extends JFrame {
    private JButton sendMessages;
    private JButton checkMessages;
    private JButton checkBroadcasts;
    private JLabel AttendeeMenu;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendeeMessageMenu(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        int userID = presenter.getUserController().getCurrentUserId();

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckUserMessages checkUserMessages = new CheckUserMessages(presenter);
                checkUserMessages.setContentPane(checkUserMessages.getMainPanel());
                checkUserMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                checkUserMessages.pack();
                checkUserMessages.setVisible(true);
                setVisible(true);
            }
        });

        sendMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendeeSendMessages attendeeSendMessages = new AttendeeSendMessages(presenter);
                attendeeSendMessages.setContentPane(attendeeSendMessages.getMainPanel());
                attendeeSendMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendeeSendMessages.pack();
                attendeeSendMessages.setVisible(true);
                setVisible(true);
            }
        });

        checkBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendeeCheckBroadcasts attendeeCheckBroadcasts = new AttendeeCheckBroadcasts();
                attendeeCheckBroadcasts.setContentPane(attendeeCheckBroadcasts.getMainPanel());
                attendeeCheckBroadcasts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendeeCheckBroadcasts.pack();
                attendeeCheckBroadcasts.setVisible(true);
                setVisible(true);
            }
        });

    }

    public void showMessages(){

    }
}
