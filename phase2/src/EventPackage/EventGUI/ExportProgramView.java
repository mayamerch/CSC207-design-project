package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventProgramPresenter;

import javax.swing.*;

public class ExportProgramView extends JFrame{
    private JPanel mainPanel;
    private JButton exportAllEventsButton;
    private JButton exportMyEventsButton;
    private JButton exportPartyEventsButton;
    private JButton exportMultiSpeakerEventsButton;
    private JButton exportSingleSpeakerEventsButton;
    private JLabel title;
    private EventProgramPresenter presenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ExportProgramView(){

    }

}
