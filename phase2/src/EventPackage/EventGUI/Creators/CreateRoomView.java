package EventPackage.EventGUI.Creators;


import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventMessagePresenter;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateRoomView extends JFrame {
    private JPanel mainPanel;
    private JLabel title;
    private JLabel capacity;
    private JButton seeRooms;
    private JTextField capacityInput;
    private JButton createButton;
    private JLabel enterInfo;
    private JLabel inputInfo;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Create a GUI responsible for Creating rooms
     * @param eventPresenter1 EventPresenter used in this GUI
     */
    public CreateRoomView(EventPresenter eventPresenter1) {
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


        createButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomCapacity = capacityInput.getText();

                boolean status = eventPresenter.createRoom(roomCapacity);
                EventMessagePresenter eventMessagePresenter = new EventMessagePresenter();
                eventMessagePresenter.CreateRoomMessage(status);
            }
        });
    }
}