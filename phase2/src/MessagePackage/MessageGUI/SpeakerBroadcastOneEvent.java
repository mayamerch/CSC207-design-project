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
                messageArea.setText(presenter.sendBroadcastToEvent(userID, eventID, broadcastMessage));
            }
        });
    }
}
