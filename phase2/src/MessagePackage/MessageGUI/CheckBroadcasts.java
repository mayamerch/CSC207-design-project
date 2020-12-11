package MessagePackage.MessageGUI;

import GUI.Presenter;
import MessagePackage.BroadcastController;
import MessagePackage.ChatroomController;

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

    public CheckBroadcasts(Presenter presenter){
        //TODO: pass user object here
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        BroadcastController broadcastController = presenter.getBroadcastController();
        int userID = presenter.getUserController().getCurrentUserId();

        showAllBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: get all Broadcasts and show in messageArea
                messageArea.append(presenter.getBroadcastController().myBroadcasts(userID));
            }
        });

        showAllBroadcasts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText(broadcastController.myBroadcasts(userID));
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
                int eventId = Integer.parseInt(enterID.getText());
                messageArea.append(presenter.getBroadcastController().returnBroadcastforEventID(eventId));
            }
        });

    }
}
