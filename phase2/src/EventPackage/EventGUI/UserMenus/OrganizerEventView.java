package EventPackage.EventGUI.UserMenus;

import EventPackage.EventGUI.AttendEventView;
import EventPackage.EventGUI.CancelAttendView;
import EventPackage.EventGUI.Creators.CreateEventView;
import EventPackage.EventGUI.Creators.CreateRoomView;
import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.Reschedule.RescheduleEventView;
import EventPackage.EventGUI.RoomView;
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
    private JPanel mainPanel;
    private JButton cancelAttend;
    private JLabel title;
    private EventController eventController;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
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
                RoomView roomView = new RoomView(eventController.getRooms());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });

        createEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEventView createEventView = new CreateEventView(eventController);
                createEventView.setContentPane(createEventView.getMainPanel());
                createEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createEventView.pack();
                createEventView.setVisible(true);
            }
        });

        createRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateRoomView createRoomView = new CreateRoomView(eventController);
                createRoomView.setContentPane(createRoomView.getMainPanel());
                createRoomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createRoomView.pack();
                createRoomView.setVisible(true);
            }
        });

        editEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RescheduleEventView rescheduleEventView = new RescheduleEventView(eventController);
                rescheduleEventView.setContentPane(rescheduleEventView.getMainPanel());
                rescheduleEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rescheduleEventView.pack();
                rescheduleEventView.setVisible(true);
            }
        });

        cancelAttend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelAttendView cancelAttendView = new CancelAttendView(eventController);
                cancelAttendView.setContentPane(cancelAttendView.getMainPanel());
                cancelAttendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cancelAttendView.pack();
                cancelAttendView.setVisible(true);
            }
        });
    }
}