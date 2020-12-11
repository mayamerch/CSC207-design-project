package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerMessageMenu extends JFrame {
    private JButton checkMessages;
    private JButton sendMessages;
    private JButton checkBroadcasts;
    private JButton messageSpeakers;
    private JButton messageAttendees;
    private JLabel OrganizerMenu;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerMessageMenu(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        //TODO: make organizer object from presenter and pass on
        int userID = presenter.getUserController().getCurrentUserId();

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageWindow messageWindow = new MessageWindow(presenter);
                messageWindow.setContentPane(messageWindow.getMainPanel());
                messageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                messageWindow.pack();
                messageWindow.setVisible(true);
                setVisible(false);
            }
        });

        sendMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessages sendMessages = new SendMessages(presenter);
                sendMessages.setContentPane(sendMessages.getMainPanel());
                sendMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sendMessages.pack();
                sendMessages.setVisible(true);
                setVisible(false); // the attendee menu
            }
        });

        checkBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBroadcasts attendeeCheckBroadcasts = new CheckBroadcasts();
                attendeeCheckBroadcasts.setContentPane(attendeeCheckBroadcasts.getMainPanel());
                attendeeCheckBroadcasts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendeeCheckBroadcasts.pack();
                attendeeCheckBroadcasts.setVisible(true);

            }
        });

        messageSpeakers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessageToAllSpeakers messageSpeakers = new SendMessageToAllSpeakers(presenter);
                messageSpeakers.setContentPane(messageSpeakers.getMainPanel());
                messageSpeakers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                messageSpeakers.pack();
                messageSpeakers.setVisible(true);
            }
        });

        messageAttendees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessageToAllAttendees messageAttendees = new SendMessageToAllAttendees(presenter);
                messageAttendees.setContentPane(messageAttendees.getMainPanel());
                messageAttendees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                messageAttendees.pack();
                messageAttendees.setVisible(true);
            }
        });

    }
}
