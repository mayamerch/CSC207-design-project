package MessagePackage.MessageGUI;

import GUI.Presenter;
import UserPackage.User;

import javax.swing.*;

public class ViewFriendsForm extends JFrame{
    private JTextArea friendListArea;
    private JLabel yourFriends;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }
     public ViewFriendsForm(){
         super();
         this.setContentPane(getMainPanel());
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.pack();
         Presenter presenter = new Presenter();

         friendListArea.append("Your friends:");
         for(int friend: presenter.getUserController().getFriendsList()){
             User your_friend = presenter.getUserController().getUserManager().getUserByID(friend);
             friendListArea.append(your_friend.getUsername());
         }

     }
}
