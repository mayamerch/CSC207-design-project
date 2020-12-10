package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeSendMessages extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton sendMessage;
    private JTextField enterID;
    private JButton viewFriends;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendeeSendMessages(){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        viewFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: pass in user to viewFriendsForm
                ViewFriendsForm viewFriendsForm = new ViewFriendsForm();
                viewFriendsForm.setContentPane(viewFriendsForm.getMainPanel());
                viewFriendsForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                viewFriendsForm.pack();
                viewFriendsForm.setVisible(true);
                setVisible(true);
            }
        });

        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText();
                String sendTo = enterID.getText();
                //TODO: send this message to the sendId
            }
        });

    }
}
