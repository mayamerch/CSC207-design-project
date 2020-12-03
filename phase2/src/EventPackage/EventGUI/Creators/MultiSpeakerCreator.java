package EventPackage.EventGUI.Creators;

import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MultiSpeakerCreator extends JFrame {
    private JPanel mainPanel;
    private JLabel name;
    private JLabel title;
    private JLabel capacity;
    private JLabel date;
    private JLabel room;
    private JLabel duration;
    private JButton seeRooms;
    private JLabel vipStatus;
    private JButton seeEvents;
    private JTextField nameInput;
    private JTextField capacityInput;
    private JTextField roomInput;
    private JTextField durationInput;
    private JComboBox booleanSelector;
    private JFormattedTextField dateInput;
    private JButton createButton;
    private JTextField speakersInput;
    private JLabel speakers;
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;
    private EventController eventController;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MultiSpeakerCreator(EventController eventController1) {
        eventController = eventController1;

        String[] choices = {"true", "false"};
        booleanSelector = new JComboBox(choices);
        booleanSelector.setSelectedIndex(1);

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
                String[] speakers = speakersInput.getText().split(",");

                int status = eventController.createMultiSpeakerEvent(eventName, eventCapacity, eventDate,
                        eventRoom, eventDuration, eventVIP, speakers);

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

                else
                    JOptionPane.showMessageDialog(null,
                            "Your Event was created successfully.",
                            "Process was successful",
                            JOptionPane.PLAIN_MESSAGE);
            }
        });

    }
}