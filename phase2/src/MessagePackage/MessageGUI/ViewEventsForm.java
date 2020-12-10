package MessagePackage.MessageGUI;

import javax.swing.*;

public class ViewEventsForm extends JFrame{
    private JTextArea eventsArea;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ViewEventsForm(){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        //TODO: pass in all events and their ids currrent user is in
        eventsArea.append("Put the events here");
    }
}
