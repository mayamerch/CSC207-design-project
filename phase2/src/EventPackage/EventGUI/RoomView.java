package EventPackage.EventGUI;

import javax.swing.*;

public class RoomView extends JFrame{
    private JPanel mainPanel;
    private JLabel title;
    private JTable roomInfo;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public RoomView(Object[][] roomsInfo) {

        Object[] header = {"Room Id", "Room Capacity"};
        roomInfo = new JTable(roomsInfo, header);
    }
}
