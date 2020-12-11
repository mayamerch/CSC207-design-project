package MessagePackage.MessageGUI;

import GUI.Presenter;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SendMessages extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton sendMessage;
    private JTextField enterID;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SendMessages(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();


        int userID = presenter.getUserController().getCurrentUserId();


        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText();
                String sendTo = enterID.getText();

                ArrayList<Integer> userlist = new ArrayList<Integer>();
                userlist.add(Integer.parseInt(sendTo));

                //messageArea.setText(presenter.getChatroomController().sendChat(userlist, userID, message));
                if(presenter.getChatroomController().sendChat(userlist, userID, message)){
                    messageArea.setText("Message Sent!");
                    presenter.saveChats();
                }
                else{
                    messageArea.setText("You are not friends with User " + sendTo + ". Failed to send.");
                }
                setVisible(true);
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
