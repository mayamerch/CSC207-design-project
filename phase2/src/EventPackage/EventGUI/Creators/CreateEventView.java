package EventPackage.EventGUI.Creators;

import EventPackage.EventOuterLayer.EventController;
import EventPackage.EventOuterLayer.EventPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEventView extends JFrame{
    private JPanel mainPanel;
    private JLabel title;
    private JButton partyButton;
    private JButton singleSpeakerButton;
    private JButton multiSpeakerButton;
    private JLabel choose;
    private EventPresenter eventPresenter;

    /**
     * returns the Main JPanel of this JFrame
     * @return the main JPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }


    public CreateEventView(EventPresenter eventPresenter1) {
        eventPresenter = eventPresenter1;

        partyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartyCreator partyCreator = new PartyCreator(eventPresenter);
                partyCreator.setContentPane(partyCreator.getMainPanel());
                partyCreator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                partyCreator.pack();
                partyCreator.setVisible(true);
                setVisible(false);
            }
        });


        singleSpeakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SingleSpeakerCreator singleSpeakerCreator = new SingleSpeakerCreator(eventPresenter);
                singleSpeakerCreator.setContentPane(singleSpeakerCreator.getMainPanel());
                singleSpeakerCreator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                singleSpeakerCreator.pack();
                singleSpeakerCreator.setVisible(true);
                setVisible(false);
            }
        });


        multiSpeakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MultiSpeakerCreator multiSpeakerCreator = new MultiSpeakerCreator(eventPresenter);
                multiSpeakerCreator.setContentPane(multiSpeakerCreator.getMainPanel());
                multiSpeakerCreator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                multiSpeakerCreator.pack();
                multiSpeakerCreator.setVisible(true);
                setVisible(false);
            }
        });
    }
}