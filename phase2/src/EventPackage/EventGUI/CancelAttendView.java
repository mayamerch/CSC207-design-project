package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAttendView extends JFrame {
    private JPanel mainPanel;
    private JTextField eventId;
    private JButton okButton;
    private JLabel title;
    private JTable eventTable;
    private JScrollPane scrollPane;
    private JLabel info;
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

    public CancelAttendView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = eventId.getText();
                int status = eventPresenter.cancelAttend(choice);

                if (status == -2)
                    JOptionPane.showMessageDialog(null,
                            "Sorry please check the format of the information inputted as it caused an error.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);
                else if (status == -1)
                    JOptionPane.showMessageDialog(null,
                            "Sorry the event you entered doesn't exist.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == 0)
                    JOptionPane.showMessageDialog(null,
                            "You aren't enrolled in the event entered",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else
                    JOptionPane.showMessageDialog(null,
                            "You are no longer enrolling in the Event",
                            "Process was unsuccessful",
                            JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private void createUIComponents() {
        eventsInfo = eventPresenter.getEventsAttending();
        eventTable = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventTable);
        title = new JLabel();
        title.setText("Events you are Currently Attending");
    }
}