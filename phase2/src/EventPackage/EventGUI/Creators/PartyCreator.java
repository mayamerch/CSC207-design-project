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
    private JLabel label1;
    private JFormattedTextField timeInput;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Create a GUI responsible for creating a Party Event.
     * @param eventPresenter1 The EventPresenter used in this view
     */
    public PartyCreator(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

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
                String eventVIP = String.valueOf(booleanSelector.getSelectedItem());

                int status = eventPresenter.createParty(eventName, eventCapacity, eventDate,
                        eventRoom, eventDuration, eventVIP);

                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.PartyCreatorMessage(status);
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