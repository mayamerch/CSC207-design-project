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
                OrganizerCheckMessages oragnizerCheckMessages = new OrganizerCheckMessages();
                oragnizerCheckMessages.setContentPane(oragnizerCheckMessages.getMainPanel());
                oragnizerCheckMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                oragnizerCheckMessages.pack();
                oragnizerCheckMessages.setVisible(true);
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
