package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizerEventView extends JFrame{
    private JButton seeEvents;
    private JButton seeMyEvents;
    private JButton attendNewEvent;
    private JButton seeRooms;
    private JButton createEvent;
    private JButton createRoom;
    private JButton editEvent;
    private JPanel labelPanel;
    private JPanel mainPanel;
    private EventController eventController;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerEventView(EventController eventController1) {
        eventController = eventController1;

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

        seeMyEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventController.getEventsAttending(), "My Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });

        attendNewEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendEventView attendView = new AttendEventView(eventController);
                attendView.setContentPane(attendView.getMainPanel());
                attendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendView.pack();
                attendView.setVisible(true);
            }
        });

        seeRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView(eventController.getAllEvents());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });

        createEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

}
}