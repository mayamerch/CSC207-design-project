package MessagePackage.MessageGUI;

import GUI.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SendMessages extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton sendMessage;
    private JTextField enterID;

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
                ArrayList<Integer> userlist = new ArrayList<>();
                userlist.add(Integer.parseInt(sendTo));
                presenter.getChatroomController().sendChat(userlist, userID, message);
                //messageArea.append("Message Sent!");
                System.out.println("Message Sent!");
                setVisible(false);
            }
        });

    }
}
