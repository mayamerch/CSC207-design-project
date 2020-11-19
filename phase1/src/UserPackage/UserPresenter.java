package UserPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class UserPresenter {

    UserManager userManager;
    Scanner sc = new Scanner(System.in);

//    public UserPresenter(UserManager userManager){
//        this.userManager = userManager;
//    }

    public void printFriendMenu(int userId){

        User currentUser = userManager.getUserByID(userId);
        boolean check = true;
        int i;
        do{
            System.out.println("What would you like to do?\n" +
                    "1. View Current Friends\n" +
                    "2. Friend Requests\n" +
                    "3. Add a new friend\n" +
                    "Press 0 to exit\n" +
                    "Please input a number: ");
            i = sc.nextInt();

            if(i==1){
                //friendlist
                printList(currentUser.getFriendsList());
            } else if (i == 2) {
                printList(currentUser.getFriendRequestList());
            }
            else if(i==3){
                //TODO: add friend requests?
            }
            else if(i==0){
                break;
            }
            else{
                System.out.println("Please try again.");
            }
        } while (0 > i || i > 3);

    }


    public String printList(ArrayList<Integer> list){
        StringBuilder display = new StringBuilder();
        for (int x: list){
            display.append(userManager.getUserByID(x).getUsername()).append("(").append(x).append("), \n");
        }
        return display.toString();
    }
}
