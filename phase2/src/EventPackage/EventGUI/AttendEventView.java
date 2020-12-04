package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendEventView extends JFrame{
    private JPanel mainPanel;
    private JTable eventInfo;
    private JLabel chooseEvent;
    private JTextField eventId;
    private JButton okButton;
    private JLabel title;
    private JScrollPane scrollPane;
    private EventPresenter eventPresenter;
    private final String[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
            "Event Date", "Event Time", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event", "Event Speaker Ids"};
    private String[][] eventsInfo;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * GUI responsible for giving an Attendee the ability to attend events
     * @param eventPresenter1 EventPresenter to be used in this view
     */
    public AttendEventView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        okButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = eventId.getText();
                boolean status = eventPresenter.signUp(choice);

                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.AttendEventMessage(status);
            }
        });
    }

    private void createUIComponents() {
        eventsInfo = eventPresenter.getAvailEvents();
        eventInfo = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventInfo);
        title = new JLabel();
        title.setText("Available Events to Attend");
    }
}