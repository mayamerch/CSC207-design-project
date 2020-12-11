package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.BroadcastController;
import MessagePackage.ChatroomController;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBroadcasts extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton showAllBroadcasts;
    private JTextField enterID;
    private JButton showBroadcastForID;
    private JButton backButton;
    private JButton viewEvents;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckBroadcasts(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        int userID = presenter.getUserController().getCurrentUserId();

        showAllBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText(presenter.getBroadcastController().myBroadcasts(userID));
            }
        });

        showBroadcastForID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eventId = Integer.parseInt(enterID.getText());
                messageArea.setText(presenter.getBroadcastController().returnBroadcastforEventID(eventId));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(presenter.getUserController().getUserType() == UserType.ATTENDEE){
                    AttendeeMessageMenu attendeeMessageMenu = new AttendeeMessageMenu(presenter);
                    attendeeMessageMenu.setVisible(true);
                    setVisible(false);
                }
                else if(presenter.getUserController().getUserType() == UserType.SPEAKER){
                    SpeakerMessageMenu speakerMessageMenu = new SpeakerMessageMenu(presenter);
                    speakerMessageMenu.setVisible(true);
                    setVisible(false);
                }
                else if(presenter.getUserController().getUserType() == UserType.ORGANIZER){
                    OrganizerMessageMenu organizerMessageMenu = new OrganizerMessageMenu(presenter);
                    organizerMessageMenu.setVisible(true);
                    setVisible(false);
                }
            }
        });


    }
}
