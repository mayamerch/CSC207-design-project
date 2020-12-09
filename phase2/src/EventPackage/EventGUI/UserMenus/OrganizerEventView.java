package EventPackage.EventGUI.UserMenus;

import EventPackage.EventGUI.*;
import EventPackage.EventGUI.Creators.CreateEventView;
import EventPackage.EventGUI.Creators.CreateRoomView;
import EventPackage.EventGUI.Reschedule.RescheduleEventView;
import EventPackage.EventOuterLayer.EventPresenter;
import EventPackage.EventOuterLayer.EventProgramPresenter;

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
    private JButton cancelEvents;
    private JButton exportHTMLProgram;
    private EventPresenter eventPresenter;
    private EventProgramPresenter programPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * GUI responsible for giving option in regards to what an Organizer can do with events
     * @param eventPresenter1 EventPresenter to be used in this view
     */
    public OrganizerEventView(EventPresenter eventPresenter1, EventProgramPresenter programPresenter) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        eventPresenter = eventPresenter1;
        this.programPresenter = programPresenter;

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

        seeMyEvents.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
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
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendEventView attendView = new AttendEventView(eventPresenter);
                attendView.setContentPane(attendView.getMainPanel());
                attendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                attendView.pack();
                attendView.setVisible(true);
            }
        });

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

        createEvent.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEventView createEventView = new CreateEventView(eventPresenter);
                createEventView.setContentPane(createEventView.getMainPanel());
                createEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createEventView.pack();
                createEventView.setVisible(true);
            }
        });

        createRoom.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateRoomView createRoomView = new CreateRoomView(eventPresenter);
                createRoomView.setContentPane(createRoomView.getMainPanel());
                createRoomView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createRoomView.pack();
                createRoomView.setVisible(true);
            }
        });

        editEvent.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                RescheduleEventView rescheduleEventView = new RescheduleEventView(eventPresenter);
                rescheduleEventView.setContentPane(rescheduleEventView.getMainPanel());
                rescheduleEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rescheduleEventView.pack();
                rescheduleEventView.setVisible(true);
            }
        });

        cancelAttend.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelAttendView cancelAttendView = new CancelAttendView(eventPresenter);
                cancelAttendView.setContentPane(cancelAttendView.getMainPanel());
                cancelAttendView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cancelAttendView.pack();
                cancelAttendView.setVisible(true);
            }
        });

        cancelEvents.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelEventView cancelEventView = new CancelEventView(eventPresenter);
                cancelEventView.setContentPane(cancelEventView.getMainPanel());
                cancelEventView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cancelEventView.pack();
                cancelEventView.setVisible(true);
            }
        });

        exportHTMLProgram.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportProgramView programMenu = new ExportProgramView(programPresenter);
                programMenu.setContentPane(programMenu.getMainPanel());
                programMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                programMenu.pack();
                programMenu.setVisible(true);

            }
        });
    }
}