package UserPackage;

import java.util.LinkedList;

public class UserGatewayTest {
    public void testGateway(){
        UserGateway gateway = new UserGateway();
        UserManager manager = new UserManager();
    }
    public static void main(String[] args){
        UserManager userManager = new UserManager();
        UserGateway userGateway = new UserGateway();
        userManager.createAccount("user1", "user1", "Organiser");
        userManager.createAccount("user2", "User2","Attendee");
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        userManager.addFriend(1, 2);

        userGateway.saveUserList(userManager.getUserList());
   
        LinkedList<User> ul = userGateway.readUserList("userFile.ser");
        System.out.println(ul.size());
    }

}
