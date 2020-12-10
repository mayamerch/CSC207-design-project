package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerMessageMenu extends JFrame {
    private JButton checkMessages;
    private JButton sendMessages;
    private JButton sendBroadcasts;
    private JButton messageSpeakers;
    private JButton messageAttendees;
    private JLabel OrganizerMenu;
    private JPanel mainPanel;
    private Presenter presenter;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerMessageMenu(Presenter presenter){
        super();
        this.presenter = presenter;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getMainPanel());
        this.pack();

        //TODO: make organizer object from presenter and pass on

        checkMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizerCheckMessages organizerCheckMessages = new OrganizerCheckMessages();
                organizerCheckMessages.setContentPane(organizerCheckMessages.getMainPanel());
                organizerCheckMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                organizerCheckMessages.pack();
                organizerCheckMessages.setVisible(true);
                setVisible(true);
            }
        });

        sendMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        sendBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        messageSpeakers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        messageAttendees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

    }
}
