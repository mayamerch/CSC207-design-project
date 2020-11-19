package UserPackage;
import EventPackage.EventManager;

import java.util.LinkedList;
import java.util.Scanner;

public class UserController {
    protected int currentUserId;
    protected UserManager userManager;
    protected UserGateway userGateway;
    Scanner scanner = new Scanner(System.in);

    public UserController(){
        this.userManager = new UserManager();
        this.userGateway = null;
        // the user manager and event manager are formed in the system controller
    }

    public UserManager getUserManager() {
        return userManager;
    }
    public void setUserManager(){
//        System.out.println("Enter the path to the saved user manager here");
//        String path = scanner.nextLine();
//        UserManager newUserManager = userGateway.readUserManager(path);
//        if (newUserManager != null){
//            this.userManager = newUserManager;
//        }
//        else{
//            this.userManager = new UserManager();
//        }

    }

    //Use this to test Conversation
    public LinkedList<User> getUserList() {
        return this.userManager.getUserList();
    }

    /**
     TODO: change this so username or ID entered, and function will get User By ID to validate
     */
    public char UserLogin() {
        System.out.println("Enter Username");
        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        int potentialID = userManager.validateLogin(username, password);
        if (potentialID >= 0 ) {
            currentUserId = potentialID;
            System.out.println("Login Successful");
            return userManager.getUserByID(currentUserId).getType();
        } else {
            System.out.println("Invalid email or password");
            return 'N';
        }
    }
    public int validateUserIDInput(){
        int userID;
        System.out.println("Enter ID of User");
        userID = scanner.nextInt();
        if (userID == currentUserId){
            System.out.println("You cannot put in your own ID");
            return validateUserIDInput();
        }
        else if (userManager.getUserByID(userID) != null){
            return userID;}
        else{
            System.out.println("That is not a Valid UserID. Please try Again.");
            return validateUserIDInput();
        }
    }

    public void saveUserList(){
        userGateway.saveUserList(getUserManager().getUserList());
    }
    public void createUser(){
        User user = userManager.getUserByID(currentUserId);
        if (user.getType() == 'O'){
            System.out.println("Enter Username of speaker");
            String username = scanner.nextLine();
            System.out.println("Enter Password for speaker");
            String password = scanner.nextLine();
            userManager.createAccount(username, password, "Speaker");
        }
        else{
            System.out.println("You are not authorized to do this");
        }
    }
    public void sendFriendRequest(){
        System.out.println("Enter Username of friend you would like to add");
        int potentialFriendId = validateUserIDInput();
        if (!userManager.sendFriendRequest(currentUserId, potentialFriendId)){
            System.out.println("You have either already sent a friend request or " +
                    "they have already accepted you as a friend");
        }
        else{
            System.out.println("Friend Request Sent");
        }
    }
    public void acceptFriendRequest(){
        System.out.println("Enter Username of friend whose request you would like to accept");
        int potentialFriendId = validateUserIDInput();
        if (!userManager.acceptFriendRequest(currentUserId, potentialFriendId)){
            System.out.println("This person has not sent you a request");
        }
        else{
            System.out.println("You have now added each other as friends");
        }
    }

}
