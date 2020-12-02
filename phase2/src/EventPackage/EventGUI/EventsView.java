package EventPackage.EventGUI;

import javax.swing.*;

public class EventsView extends JFrame{
    private JPanel mainPanel;
    private JLabel title;
    private JTable eventInfo;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public EventsView(Object[][] eventsInfo, String title) {
        this.title.setText(title);

        Object[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
                "Event Date", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event"};
        eventInfo = new JTable(eventsInfo, header);
    }
}