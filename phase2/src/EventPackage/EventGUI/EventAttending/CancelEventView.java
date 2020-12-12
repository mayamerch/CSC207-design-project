package EventPackage.EventGUI.EventAttending;

import EventPackage.EventGUI.Reschedule.EditMultiSpeaker;
import EventPackage.EventGUI.Reschedule.EditParty;
import EventPackage.EventGUI.Reschedule.EditSingleSpeaker;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelEventView extends JFrame {
    private JPanel mainPanel;
    private JLabel title;
    private JLabel choose;
    private JLabel id;
    private JTextField eventId;
    private JButton next;
    private JScrollPane scrollPane;
    private JTable eventInfo;
    private EventPresenter eventPresenter;
    private String[][] eventsInfo;
    private final String[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
            "Event Date", "Event Time", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event"};

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }


    /**
     * Create a GUI responsible for choosing an event to cancel.
     * @param eventPresenter1 The EventPresenter used in this view
     */
    public CancelEventView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;


        next.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean status = eventPresenter.cancelEvent(eventId.getText());
                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.CancelEventMessage(status);
            }
        });
    }

    private void createUIComponents() {
        eventsInfo = eventPresenter.getAllEvents();
        eventInfo = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventInfo);
    }
}