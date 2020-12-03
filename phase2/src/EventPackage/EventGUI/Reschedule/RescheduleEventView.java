package EventPackage.EventGUI.Reschedule;

import EventPackage.EventEntities.SingleSpeakerEvent;
import EventPackage.EventGUI.Creators.PartyCreator;
import EventPackage.EventOuterLayer.EventController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RescheduleEventView extends JFrame {
    private JPanel mainPanel;
    private JLabel title;
    private JLabel choose;
    private JTextField eventId;
    private JLabel id;
    private JTable eventInfo;
    private JButton next;
    private EventController eventController;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public RescheduleEventView(EventController eventController1) {
        eventController = eventController1;
        Object[][] eventsInfo = eventController.getAllEvents();

        Object[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
                "Event Date", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event"};

        eventInfo = new JTable(eventsInfo, header);


        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String EventID = eventId.getText();
                int status = eventController.findType(EventID);

                if (status == -1)
                    JOptionPane.showMessageDialog(null,
                            "Sorry please check the format of the information inputted as it caused an error.",
                            "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == 0)
                    JOptionPane.showMessageDialog(null,
                            "The Event you have entered is not available", "Process was unsuccessful",
                            JOptionPane.ERROR_MESSAGE);

                else if (status == 1) {
                    EditParty editParty = new EditParty(eventController, EventID);
                    editParty.setContentPane(editParty.getMainPanel());
                    editParty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editParty.pack();
                    editParty.setVisible(true);
                    setVisible(false);
                }

                else if (status == 2) {
                    EditSingleSpeaker editSingleSpeaker = new EditSingleSpeaker(eventController, EventID);
                    editSingleSpeaker.setContentPane(editSingleSpeaker.getMainPanel());
                    editSingleSpeaker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editSingleSpeaker.pack();
                    editSingleSpeaker.setVisible(true);
                    setVisible(false);
                }

                else if (status == 3) {
                    EditMultiSpeaker editMultiSpeaker = new EditMultiSpeaker(eventController, EventID);
                    editMultiSpeaker.setContentPane(editMultiSpeaker.getMainPanel());
                    editMultiSpeaker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    editMultiSpeaker.pack();
                    editMultiSpeaker.setVisible(true);
                    setVisible(false);
                }

            }
        });
    }
}