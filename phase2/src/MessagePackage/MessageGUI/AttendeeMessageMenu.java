package MessagePackage.MessageGUI;

import GUI.Presenter;

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
                // TODO: get rid of checkusermessages? to simplify?
                /*CheckMessages checkUserMessages = new CheckMessages(presenter);
                checkUserMessages.setContentPane(checkUserMessages.getMainPanel());
                checkUserMessages.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                checkUserMessages.pack();
                checkUserMessages.setVisible(true);
                setVisible(true);*/

                MessageWindow messageWindow = new MessageWindow(presenter);
                messageWindow.setContentPane(messageWindow.getMainPanel());
                messageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                messageWindow.pack();
                messageWindow.setVisible(true);
                setVisible(false); // the attendee menu
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
                setVisible(false); // the attendee menu

            }
        });

    }

    public void showMessages(){ }
}
