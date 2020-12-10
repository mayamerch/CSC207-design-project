package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerBroadcastAllEvents extends JFrame{
    private JTextArea messageArea;
    private JButton sendBroadcastButton;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SpeakerBroadcastAllEvents(){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        sendBroadcastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String broadcastMessage = messageArea.getText();
                //TODO: send broadcast to all events
            }
        });
    }
}
