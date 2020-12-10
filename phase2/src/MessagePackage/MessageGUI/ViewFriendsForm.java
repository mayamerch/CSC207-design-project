package MessagePackage.MessageGUI;

import javax.swing.*;

public class ViewFriendsForm extends JFrame{
    private JTextArea friendListArea;
    private JLabel label;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }
     public ViewFriendsForm(){
         super();
         this.setContentPane(getMainPanel());
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.pack();

         //TODO: add ids to textarea
         friendListArea.append("Display friends usernames and IDs here");

     }
}
