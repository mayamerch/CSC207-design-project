package UserPackage;

import java.util.Collection;
import java.util.LinkedList;


public class UserPresenter {

    private UserManager userManager;

    /**
     * Creates a UserPresenter
     * @param userManager The Usermanager that the presenter will reference in its methods
     */
    public UserPresenter(UserManager userManager){
        this.userManager = userManager;
    }

    //returns a LinkedList of strings formatted as follows: "username (userID)"
    /**
     * Takes a List of User objects and outputs a list of strings where each element is a User's
     * Username and ID
     * @param userCollection the List of User objects
     * @return List of Strings, each one being a User's username and ID
     */
    private LinkedList<String> userListToString(Collection<User> userCollection){
        LinkedList<String> list = new LinkedList<>();
        for(User x: userCollection){
            list.add(x.getUsername() + " (" + x.getUserID() + ")");
        }
        return list;
    }
    //converts as list of ids to a linked list of users
    private LinkedList<User> userIDListToUserList(Collection<Integer> userIDCollection){
        LinkedList<User> list = new LinkedList<>();
        for (int x: userIDCollection){
            list.add(userManager.getUserByID(x));
        }
        return list;
    }


    //returns a LinkedList of strings formatted as follows: "username (userID)"
    //uses userListToString() and userIDListToUserList() as a helper method
    /**
     * Takes a List of UserIDs and outputs a list of strings where each element is a User's
     * Username and ID
     * @param userIDCollection the List of UserIDs, List of Integers
     * @return List of Strings, each String being a User's username and ID
     */
    public LinkedList<String> userIDListToString(Collection<Integer> userIDCollection) {
        return userListToString(userIDListToUserList(userIDCollection));
    }

}
    /*UserManager userManager;
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

    public void printMenu(){
        StringBuilder temp = new StringBuilder();

        temp.append("Manage Connections" + System.lineSeparator());
        temp.append("---------------------------------" + System.lineSeparator());

        temp.append("1. See all Friends" + System.lineSeparator());
        temp.append("2. See all Friend Requests" + System.lineSeparator());

        temp.append("0. Exit this menu." + System.lineSeparator() + System.lineSeparator());

        temp.append("Please choose an option number." + System.lineSeparator());
        System.out.print(temp);
    }


    public String printList(List<Integer> list){
        StringBuilder display = new StringBuilder();
        for (int x: list){
            display.append(userManager.getUserByID(x).getUsername()).append("(").append(x).append("), \n");
        }
        return display.toString();
    }

    public void goBack() {
        System.out.println("Going back to previous menu." + System.lineSeparator());
    }

    public void denyUser()  {
        System.out.println("This option does not exist." + System.lineSeparator());
    }

}*/
