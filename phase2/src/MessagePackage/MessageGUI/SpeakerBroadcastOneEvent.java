package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerBroadcastOneEvent extends JFrame{
    private JTextArea messageArea;
    private JButton sendBroadcastButton;
    private JTextField enterEventID;
    private JPanel mainPanel;
    private JTextField enterBroadcastTextField;
    private JTextArea broadcastMessage;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SpeakerBroadcastOneEvent(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        int userID = presenter.getUserController().getCurrentUserId();

        sendBroadcastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eventID = Integer.parseInt(enterEventID.getText());
                String broadcastMessage = messageArea.getText();
                //TODO: send broadcast to event with eventID
                presenter.getBroadcastController().sendBroadcastToEvent(userID, eventID, broadcastMessage);
                messageArea.append("Broadcast sent.");
            }
        });

        //TODO: can we get rid of this?
        /*viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEventsForm viewEventsForm = new ViewEventsForm();
                viewEventsForm.setContentPane(viewEventsForm.getMainPanel());
                viewEventsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewEventsForm.pack();
                viewEventsForm.setVisible(true);
                setVisible(true);
            }
        });*/

    }

}
