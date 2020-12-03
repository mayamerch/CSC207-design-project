package EventPackage.EventGUI.Reschedule;

import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EditMultiSpeaker extends JFrame {
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
    private JLabel info1;
    private JLabel info2;
    private JLabel info3;
    private JLabel info4;
    private JLabel info5;
    private JLabel currName;
    private JLabel currCapacity;
    private JLabel currDate;
    private JLabel currRoom;
    private JLabel currDuration;
    private JLabel currVIP;
    private JLabel info6;
    private JLabel currID;
    private JLabel speakerIds;
    private JTextField speakersInput;
    private JLabel info7;
    private JLabel currSpeakers;
    private JLabel label1;

    private EventController eventController;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public EditMultiSpeaker(EventController eventController1, String currEventId) {
        eventController = eventController1;

        String[] choices = {"true", "false"};
        booleanSelector = new JComboBox(choices);
        booleanSelector.setSelectedIndex(1);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        dateInput = new JFormattedTextField(dateFormat);

        currID.setText(currEventId);

        String[] eventInfo = eventController.getMultiSpeakerInfo(currEventId);

        currName.setText(eventInfo[0]);
        currCapacity.setText(eventInfo[1]);
        currDate.setText(eventInfo[2]);
        currRoom.setText(eventInfo[3]);
        currDuration.setText(eventInfo[4]);
        currVIP.setText(eventInfo[5]);
        currSpeakers.setText(eventInfo[6]);


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
                String eventSpeaker = speakersInput.getText();

                int status = eventController.editMultiSpeaker(currEventId, eventName, eventCapacity, eventDate,
                        eventRoom, eventDuration, eventVIP, eventSpeaker);

                if (status == -2)
                    JOptionPane.showMessageDialog(null,
                            "Sorry please check the format of the information inputted as it caused an error.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == -1)
                    JOptionPane.showMessageDialog(null,
                            "Sorry this event couldn't be edited" +
                                    " due to new information overlapping with another event.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == -4)
                    JOptionPane.showMessageDialog(null,
                            "Sorry this event couldn't be created" +
                                    " as the room or one of the speakers don't exist.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);


                else
                    JOptionPane.showMessageDialog(null,
                            "Your Event was edited successfully.",
                            "Process was successful",
                            JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}