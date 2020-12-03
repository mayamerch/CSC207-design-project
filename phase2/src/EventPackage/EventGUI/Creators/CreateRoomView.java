package EventPackage.EventGUI.Creators;


import EventPackage.EventGUI.RoomView;
import EventPackage.EventOuterLayer.EventController;

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
    private EventController eventController;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CreateRoomView(EventController eventController1) {
        eventController = eventController1;

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


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomCapacity = capacityInput.getText();

                boolean status = eventController.createRoom(roomCapacity);

                if (!status)
                    JOptionPane.showMessageDialog(null,
                            "Sorry please check the format of the information inputted as it caused an error.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else
                    JOptionPane.showMessageDialog(null,
                            "Your Room was created successfully.",
                            "Process was successful",
                            JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}