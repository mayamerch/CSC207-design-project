package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerBroadcastOneEvent extends JFrame{
    private JTextArea messageArea;
    private JButton viewEventsButton;
    private JButton sendBroadcastButton;
    private JTextField enterEventID;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SpeakerBroadcastOneEvent(){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        sendBroadcastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventID = enterEventID.getText();
                String broadcastMessage = messageArea.getText();
                //TODO: send broadcast to event with eventID
            }
        });

        viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEventsForm viewEventsForm = new ViewEventsForm();
                viewEventsForm.setContentPane(viewEventsForm.getMainPanel());
                viewEventsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewEventsForm.pack();
                viewEventsForm.setVisible(true);
                setVisible(true);
            }
        });

    }

}
