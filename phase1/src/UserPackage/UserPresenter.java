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


    }
    public String printList(ArrayList<Integer> list){
        StringBuilder display = new StringBuilder();
        for (int x: list){
            display.append(userManager.getUserByID(x).getUsername()).append("(").append(x).append("), \n");
        }
        return display.toString();
    }
}
