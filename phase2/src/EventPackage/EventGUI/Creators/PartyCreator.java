package EventPackage.EventGUI.Creators;

import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PartyCreator extends JFrame {
    private JPanel mainPanel;
    private JLabel title;
    private JButton seeRooms;
    private JLabel name;
    private JLabel capacity;
    private JLabel date;
    private JLabel room;
    private JLabel duration;
    private JLabel vipStatus;
    private JButton seeEvents;
    private JTextField nameInput;
    private JComboBox<String> booleanSelector;
    private JTextField durationInput;
    private JTextField roomInput;
    private JTextField capacityInput;
    private JFormattedTextField dateInput;
    private JButton createButton;
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel label1;
    private EventController eventController;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public PartyCreator(EventController eventController1) {
        eventController = eventController1;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        dateInput = new JFormattedTextField(dateFormat);


        seeRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView(eventController.getRooms());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });


        seeEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventController.getAllEvents(), "All the Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = nameInput.getText();
                String eventCapacity = capacityInput.getText();
                String eventDate = dateInput.getText();
                String eventRoom = roomInput.getText();
                String eventDuration = durationInput.getText();
                String eventVIP = (String) booleanSelector.getSelectedItem();

                int status = eventController.createParty(eventName, eventCapacity, eventDate,
                        eventRoom, eventDuration, eventVIP);

                if (status == -2)
                    JOptionPane.showMessageDialog(null,
                            "Sorry please check the format of the information inputted as it caused an error.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == -1)
                    JOptionPane.showMessageDialog(null,
                            "Sorry this event couldn't be created" +
                                    " due to overlap with another event.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == -3)
                    JOptionPane.showMessageDialog(null,
                            "Sorry this event couldn't be created" +
                                    " due to its capacity exceeding the capacity of its room.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == -4)
                    JOptionPane.showMessageDialog(null,
                            "Sorry this event couldn't be created" +
                                    " as the room doesn't exist.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else
                    JOptionPane.showMessageDialog(null,
                            "Your Event was created successfully.",
                            "Process was successful",
                            JOptionPane.PLAIN_MESSAGE);
            }
        });

    }

    private void createUIComponents() {
        String[] choices = new String[] {"true", "false"};
        booleanSelector = new JComboBox<>(choices);
        booleanSelector.setSelectedIndex(1);
    }
}