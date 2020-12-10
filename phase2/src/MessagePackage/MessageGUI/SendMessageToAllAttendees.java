package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageToAllAttendees extends JFrame{
    private JTextArea messageArea;
    private JButton sendMessagesButton;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SendMessageToAllAttendees(){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        sendMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText();
                //TODO: send message to all attendees
            }
        });
    }
}
