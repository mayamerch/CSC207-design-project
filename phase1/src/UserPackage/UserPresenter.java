package UserPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class UserPresenter {

    UserManager userManager;
    Scanner sc = new Scanner(System.in);

    public UserPresenter(UserManager userManager){
        this.userManager = userManager;
    }

    public void printFriendMenu(int userId){

        User currentUser = userManager.getUserByID(userId);

        System.out.println("What would you like to do?\n" +
                "1. View Current Friends\n" +
                "2. Friend Requests\n" +
                "3. Add a new friend\n" +
                "Please input a number: ");
        Integer i = sc.nextInt();

        switch (i){
            case 1:
                System.out.println(printList(currentUser.getFriendsList()));
                break;
            case 2:
                System.out.println(printList(currentUser.getFriendRequestList()));
                break;
            case 3: //Create Add Friend  ;
        }

    }
    public String printList(ArrayList<Integer> list){
        String display = "";
        for (int x: list){
            display = display + userManager.getUserByID(x).getUsername() +"("+x+"), \n";
        }
        return display;
    }
}
