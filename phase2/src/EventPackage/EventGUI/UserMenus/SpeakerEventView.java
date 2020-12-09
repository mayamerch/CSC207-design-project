package EventPackage.EventGUI.UserMenus;

import EventPackage.EventEntities.Event;
import EventPackage.EventGUI.EventsView;
import EventPackage.EventGUI.ExportProgramView;
import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventPresenter;
import EventPackage.EventOuterLayer.EventProgramPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerEventView extends JFrame{
    private JPanel mainPanel;
    private JButton seeEvents;
    private JButton seeMyEvents;
    private JLabel title;
    private JButton seeRooms;
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
     * GUI responsible for giving option in regards to what an Speaker can do with events
     * @param eventPresenter1 EventPresenter to be used in this view
     */
    public SpeakerEventView(EventPresenter eventPresenter1, EventProgramPresenter programPresenter) {
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        this.eventPresenter = eventPresenter1;
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
                EventsView eventsView = new EventsView(eventPresenter.getEventsSpeakingAt(), "My Events");
                eventsView.setContentPane(eventsView.getMainPanel());
                eventsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                eventsView.pack();
                eventsView.setVisible(true);
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