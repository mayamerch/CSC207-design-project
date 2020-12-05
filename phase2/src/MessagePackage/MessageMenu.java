package MessagePackage;

import EventPackage.EventGUI.EventsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageMenu extends JFrame{
    private JPanel mainPanel;
    private JButton viewConversations;
    private JButton viewBroadcasts;
    private JLabel title;
    private JButton newButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MessageMenu(){

        viewConversations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Conversation Menu here
                ConversationMenu conversationMenu = new ConversationMenu();
                conversationMenu.setContentPane(conversationMenu.getMainPanel());
                conversationMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                conversationMenu.pack();
                conversationMenu.setVisible(true);
            }
        });

        viewBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Broadcast Menu here
                BroadcastMenu broadcastMenu = new BroadcastMenu();
                broadcastMenu.setContentPane(broadcastMenu.getMainPanel());
                broadcastMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                broadcastMenu.pack();
                broadcastMenu.setVisible(true);
            }
        });

    }
}
