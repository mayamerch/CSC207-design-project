package UserPackage;

import java.util.HashMap;
import java.util.Map;

public class UserGatewayTest {
    public void testGateway(){
        UserGateway gateway = new UserGateway();
        UserManager manager = new UserManager();
    }
    public static void main(String[] args){
        UserManager userManager = new UserManager();
        UserGateway userGateway = new UserGateway();
        userManager.createAccount("user1", "user1", UserType.ORGANIZER);
        userManager.createAccount("user2", "User2",UserType.ATTENDEE);
        User user1 = userManager.getUserByID(1);
        User user2 = userManager.getUserByID(2);
        userManager.addFriend(1, 2);

        userGateway.saveUserMap(userManager.getUserMap());
        Map<Integer, User> ul = userGateway.readUserMap();
        System.out.println(ul.size());
    }

}
