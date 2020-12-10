package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerSendMessages extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JTextField enterUserID;
    private JButton sendMessage;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerSendMessages(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getMainPanel());
        this.pack();
        //TODO: pass on organizer object

        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recepientID = enterUserID.getText();
                String message = messageArea.getText();
                //TODO: send message to the user
            }
        });
    }
}
