package MessagePackage.MessageGUI;

import GUI.MainMenuView;
import GUI.Presenter;
import GUI.UserMenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeMessageMenu extends JFrame {
    private JButton sendMessages;
    private JButton checkMessages;
    private JButton checkBroadcasts;
    private JLabel AttendeeMenu;
    private JPanel mainPanel;
    private JButton backButton;

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
                setVisible(false);

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }

    public void showMessages(){ }
}
