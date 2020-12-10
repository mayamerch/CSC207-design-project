package MessagePackage.MessageGUI;

import javax.swing.*;

public class OrganizerCheckMessages extends JFrame{
    private JTextArea messageArea;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public OrganizerCheckMessages(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getMainPanel());
        this.pack();

        messageArea.setText("messages here");
    }
}
