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
    private JScrollPane scrollPane;
    private EventController eventController;
    private final String[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
            "Event Date", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event", "Event Speaker Ids"};
    private String[][] eventsInfo;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendEventView(EventController eventController1) {
        eventController = eventController1;

        eventsInfo = eventController.getAvailEvents();


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

    private void createUIComponents() {
        eventInfo = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventInfo);
        title = new JLabel();
        title.setText("Available Events to Attend");
    }
}