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

        //TODO: make user object from presenter and pass on

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendeeCheckMessages attendeeCheckMessages = new AttendeeCheckMessages();
                attendeeCheckMessages.setContentPane(attendeeCheckMessages.getMainPanel());
                attendeeCheckMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendeeCheckMessages.pack();
                attendeeCheckMessages.setVisible(true);
                setVisible(true);
            }
        });

        sendMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        checkBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

    }

    public void showMessages(){

    }
}
