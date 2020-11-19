package UserPackage;

import java.util.Scanner;

public class UserPresenter {

    public void printFriendMenu(int userId){
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "1. View Current Friends\n" +
                "2. Friend Requests\n" +
                "3. Add a new friend\n" +
                "4. Add a new friend\n" +
                "Please input a number: ");
        Integer i = sc.nextInt();

    }
}
