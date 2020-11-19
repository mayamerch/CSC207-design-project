package UserPackage;

import java.util.Scanner;

public class UserPresenter {

    public void printFriendMenu(int userId){
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();
        User loggedIn = userManager.getUserByID(userId);

        System.out.println("What would you like to do?\n" +
                "1. View Current Friends\n" +
                "2. Friend Requests\n" +
                "3. Add a new friend\n" +
                "Please input a number: ");
        Integer i = sc.nextInt();

        switch (i){
            case 1: System.out.println(loggedIn.getFriendsList());
            case 2: System.out.println(loggedIn.getFriendRequestList());
            case 3: //Create Add Friend  ;
        }

    }
}
