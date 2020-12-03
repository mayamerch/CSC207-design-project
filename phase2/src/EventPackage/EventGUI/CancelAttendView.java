package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAttendView extends JFrame {
    private JPanel mainPanel;
    private JTable eventInfo;
    private JLabel chooseEvent;
    private JTextField eventId;
    private JButton okButton;
    private JLabel title;
    private EventController eventController;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CancelAttendView(EventController eventController1) {
        eventController = eventController1;

        Object[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
                "Event Date", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event"};

        eventInfo = new JTable(eventController.getEventsAttending(), header);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = eventId.getText();
                int status = eventController.cancelAttend(choice);

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
}