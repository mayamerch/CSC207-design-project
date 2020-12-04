package EventPackage.EventGUI;

import javax.swing.*;

public class RoomView extends JFrame{
    private JPanel mainPanel;
    private JLabel title;
    private JTable roomInfo;
    private JScrollPane scrollPane;
    private final String[] header = {"Room Id", "Room Capacity"};
    private String[][] roomsInfo;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Gui that presents rooms in a table format
     * @param roomsInfo Info about the rooms
     */
    public RoomView(String[][] roomsInfo) {
        this.roomsInfo = roomsInfo;
    }

    private void createUIComponents() {
        roomInfo = new JTable(roomsInfo, header);
        scrollPane = new JScrollPane(roomInfo);
    }
}
