package MessagePackage.MessageGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBroadcasts extends JFrame{
    private JPanel mainPanel;
    private JTextArea messageArea;
    private JButton showAllBroadcasts;
    private JTextField enterID;
    private JButton showBroadcastForID;
    private JButton viewEvents;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckBroadcasts(){
        //TODO: pass user object here
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        showAllBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: get all Broadcasts and show in messageArea
            }
        });

        viewEvents.addActionListener(new ActionListener() {
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

        showBroadcastForID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = enterID.getText();
                //TODO: use this idea to display selected broadcasts
            }
        });

    }
}
