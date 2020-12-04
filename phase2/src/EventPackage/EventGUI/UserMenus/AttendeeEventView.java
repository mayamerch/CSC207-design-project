package EventPackage.EventGUI.UserMenus;

import EventPackage.EventGUI.AttendEventView;
import EventPackage.EventGUI.CancelAttendView;
import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendeeEventView extends JFrame {


    private JPanel mainPanel;
    private JButton seeEvents;
    private JButton seeMyEvents;
    private JButton attendNewEvent;
    private JButton seeRooms;
    private JButton cancelAttend;
    private JLabel title;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AttendeeEventView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        seeEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventPresenter.getAllEvents(), "All the Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });

        seeMyEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventsView eventsView = new EventsView(eventPresenter.getEventsAttending(), "My Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });

        attendNewEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendEventView attendView = new AttendEventView(eventPresenter);
                attendView.setContentPane(attendView.getMainPanel());
                attendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendView.pack();
                attendView.setVisible(true);
            }
        });

        cancelAttend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelAttendView cancelAttendView = new CancelAttendView(eventPresenter);
                cancelAttendView.setContentPane(cancelAttendView.getMainPanel());
                cancelAttendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cancelAttendView.pack();
                cancelAttendView.setVisible(true);
            }
        });

        seeRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = new RoomView(eventPresenter.getRooms());
                roomView.setContentPane(roomView.getMainPanel());
                roomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                roomView.pack();
                roomView.setVisible(true);
            }
        });
    }
}