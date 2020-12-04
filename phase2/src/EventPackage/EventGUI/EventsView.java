package EventPackage.EventGUI;

import javax.swing.*;

public class EventsView extends JFrame{
    private JPanel mainPanel;
    private JLabel title;
    private JTable eventInfo;
    private JScrollPane scrollPane;
    private final String[] header = {"Event Id", "Event Name", "Event Type", "Event Room",
            "Event Date", "Event Time", "Event Duration", "Event Capacity", "Available Spaces", "VIP Event", "Event Speaker Ids"};
    private String[][] eventsInfo;
    private String titleParam;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * GUI responsible for presenting events in a table format
     * @param eventsInfo Info about the events to be presented
     * @param title title of this table
     */
    public EventsView(String[][] eventsInfo, String title) {
        titleParam = title;
        this.eventsInfo = eventsInfo;
    }

    private void createUIComponents() {
        eventInfo = new JTable(eventsInfo, header);
        scrollPane = new JScrollPane(eventInfo);
        title = new JLabel();
        title.setText(titleParam);
    }
}