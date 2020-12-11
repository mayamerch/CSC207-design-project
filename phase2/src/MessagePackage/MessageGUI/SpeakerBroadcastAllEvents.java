package MessagePackage.MessageGUI;

import GUI.Presenter;
import UserPackage.Speaker;
import UserPackage.User;
import UserPackage.UserType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerBroadcastAllEvents extends JFrame{
    private JTextArea messageArea;
    private JButton sendBroadcastButton;
    private JPanel mainPanel;
    private JLabel EnterBroadcastMessage;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SpeakerBroadcastAllEvents(Presenter presenter){
        super();
        this.setContentPane(getMainPanel());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        int userID = presenter.getUserController().getCurrentUserId();
        User speaker = presenter.getUserController().getUserManager().getUserByID(userID);

        sendBroadcastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String broadcastMessage = messageArea.getText();
                messageArea.setText(presenter.sendBroadcastInAllSpeakerEvents((Speaker)speaker, broadcastMessage));
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpeakerMessageMenu speakerMessageMenu = new SpeakerMessageMenu(presenter);
                speakerMessageMenu.setVisible(true);
                setVisible(false);
            }
        });
    }
}
