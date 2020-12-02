package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventController;

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
    private EventController eventController;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendEventView(EventController eventController1) {
        eventController = eventController1;

        Object[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
                "Event Date", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event"};

        eventInfo = new JTable(eventController.getAvailEvents(), header);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = eventId.getText();
                boolean status = eventController.signUp(choice);

                if (status)
                    JOptionPane.showMessageDialog(null,
                            "You have successfully been enrolled in the event", "Process was successful",
                            JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,
                            "The Event you have entered is not available", "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}