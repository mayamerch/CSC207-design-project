package EventPackage.EventGUI.Creators;

import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SingleSpeakerCreator extends JFrame {
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
    private JTextField speakerInput;
    private JLabel speaker;
    private JLabel info1;
    private JLabel info2;
    private JLabel label1;
    private JFormattedTextField timeInput;
    private JLabel info3;
    private JLabel speakerInfo;
    private JLabel existingIds;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }


    /**
     * Create a GUI responsible for creating a Single Speaker Event.
     * @param eventPresenter1 The EventPresenter used in this view
     */
    public SingleSpeakerCreator(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;
        existingIds.setText(eventPresenter.getSpeakerIds());

        seeRooms.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView(eventPresenter.getRooms());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });


        seeEvents.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventPresenter.getAllEvents(), "All the Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });


        createButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = nameInput.getText();
                String eventCapacity = capacityInput.getText();
                String eventDate = dateInput.getText() + "-" + timeInput.getText();
                String eventRoom = roomInput.getText();
                String eventDuration = durationInput.getText();
                String eventVIP = (String) booleanSelector.getSelectedItem();
                String speaker = speakerInput.getText();

                int status = eventPresenter.createSingleSpeakerEvent(eventName, eventCapacity, eventDate,
                        eventRoom, eventDuration, eventVIP, speaker);

                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.SingleSpeakerCreatorMessage(status);
            }
        });

    }

    private void createUIComponents() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateInput = new JFormattedTextField(dateFormat);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeInput = new JFormattedTextField(timeFormat);

        String[] choices = new String[] {"true", "false"};
        booleanSelector = new JComboBox<>(choices);
        booleanSelector.setSelectedIndex(1);
    }
}