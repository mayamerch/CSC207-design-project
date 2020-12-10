package EventPackage.EventGUI;

import EventPackage.EventOuterLayer.EventProgramPresenter;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportProgramView extends JFrame{
    private JPanel mainPanel;
    private JButton exportAllEventsButton;
    private JButton exportMyEventsButton;
    private JButton exportPartyEventsButton;
    private JButton exportMultiSpeakerEventsButton;
    private JButton exportSingleSpeakerEventsButton;
    private JLabel title;
    private EventProgramPresenter programPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ExportProgramView(EventProgramPresenter programPresenter){
        this.programPresenter = programPresenter;

        exportAllEventsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                programPresenter.openAllEventsProgram();
            }
        });

        exportMyEventsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                programPresenter.openMyEventsProgram();
            }
        });

        exportPartyEventsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                programPresenter.openPartyEventsProgram();
            }
        });

        exportMultiSpeakerEventsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                programPresenter.openMultiSpeakerEventsProgram();
            }
        });

        exportSingleSpeakerEventsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e Button is pressed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                programPresenter.openSingleSpeakerEventsProgram();
            }
        });
    }

}
