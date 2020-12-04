package EventPackage.EventGUI.Reschedule;

import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RescheduleEventView extends JFrame {
    private JPanel mainPanel;
    private JLabel title;
    private JLabel choose;
    private JTextField eventId;
    private JLabel id;
    private JTable eventInfo;
    private JButton next;
    private JScrollPane scrollPane;
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

    public RescheduleEventView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String EventID = eventId.getText();
                int status = eventPresenter.findType(EventID);

                if (status == -1 || status == 0) {
                    EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                    eventMessagePresenter.rescheduleEventMessage(status);
                }

                else if (status == 1) {
                    EditParty editParty = new EditParty(eventPresenter, EventID);
                    editParty.setContentPane(editParty.getMainPanel());
                    editParty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editParty.pack();
                    editParty.setVisible(true);
                    setVisible(false);
                }

                else if (status == 2) {
                    EditSingleSpeaker editSingleSpeaker = new EditSingleSpeaker(eventPresenter, EventID);
                    editSingleSpeaker.setContentPane(editSingleSpeaker.getMainPanel());
                    editSingleSpeaker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editSingleSpeaker.pack();
                    editSingleSpeaker.setVisible(true);
                    setVisible(false);
                }

                else if (status == 3) {
                    EditMultiSpeaker editMultiSpeaker = new EditMultiSpeaker(eventPresenter, EventID);
                    editMultiSpeaker.setContentPane(editMultiSpeaker.getMainPanel());
                    editMultiSpeaker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editMultiSpeaker.pack();
                    editMultiSpeaker.setVisible(true);
                    setVisible(false);
                }

            }
        });
    }

    private void createUIComponents() {
        eventsInfo = eventPresenter.getAllEvents();
        eventInfo = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventInfo);
        title = new JLabel();
        title.setText("Edit an Event");
    }
}