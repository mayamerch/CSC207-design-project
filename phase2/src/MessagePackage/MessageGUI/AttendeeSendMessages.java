package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttendeeSendMessages extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton sendMessage;
    private JTextField enterID;
    private JButton viewFriends;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendeeSendMessages(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();


        int userID = presenter.getUserController().getCurrentUserId();

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
                ArrayList<Integer> userlist = new ArrayList<>();
                userlist.add(Integer.parseInt(sendTo));
                presenter.getChatroomController().sendChat(userlist, userID, message);
            }
        });

    }
}
