package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerMessageMenu extends JFrame {
    private JButton checkMessages;
    private JButton sendMessages;
    private JButton checkBroadcasts;
    private JButton broadcastOneEvent;
    private JButton broadcastAllEvents;
    private JLabel SpeakerMenu;
    private JPanel mainPanel;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SpeakerMessageMenu(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckMessages checkUserMessages = new CheckMessages(presenter);
                checkUserMessages.setContentPane(checkUserMessages.getMainPanel());
                checkUserMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                checkUserMessages.pack();
                checkUserMessages.setVisible(true);
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
                CheckBroadcasts attendeeCheckBroadcasts = new CheckBroadcasts(presenter);
                attendeeCheckBroadcasts.setContentPane(attendeeCheckBroadcasts.getMainPanel());
                attendeeCheckBroadcasts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendeeCheckBroadcasts.pack();
                attendeeCheckBroadcasts.setVisible(true);
            }
        });

        broadcastOneEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpeakerBroadcastOneEvent oneEvent = new SpeakerBroadcastOneEvent(presenter);
                oneEvent.setContentPane(oneEvent.getMainPanel());
                oneEvent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                oneEvent.pack();
                oneEvent.setVisible(true);
            }
        });

        broadcastAllEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpeakerBroadcastAllEvents allEvents = new SpeakerBroadcastAllEvents(presenter);
                allEvents.setContentPane(allEvents.getMainPanel());
                allEvents.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                allEvents.pack();
                allEvents.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }
}
