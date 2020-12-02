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
                AllEventsView eventsView = new AllEventsView(eventController.getAllEvents());
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
            }
        });

        seeMyEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        attendNewEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        seeRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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