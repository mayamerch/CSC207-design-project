package UserPackage;

import java.util.LinkedList;
import java.util.Scanner;

public class UserController {
    public int currentUserId;
    protected UserManager userManager;
    protected UserGateway userGateway;
    Scanner scanner = new Scanner(System.in);
    /**
     * Constructs a new UserController object, setting the user manager based off of the gateway
     */
    public UserController(){
        this.userGateway = new UserGateway();
        this.userManager = new UserManager(this.userGateway.readUserList("src/UserPackage/userFile.ser"));
        // Add something so if this fails it calls on the no argument constructor
    }
    /**
     * Returns the userManager attribute of the User Controller
     * @return userManager, the UserManager Attribute of the controler
     */
    public UserManager getUserManager() {
        return userManager;
    }


    /**
     * Logs in the User based on the Username and Password entered by the User
     * @return the type of the User that logged in, character
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
    /**
     * Checks a userID that is entered into the system
     */
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
    /**
     * Accesses the gateway to save the List of Users contained within the UserManager attribute of this
     * Controller
     */
    public void saveUserList(){
        userGateway.saveUserList(getUserManager().getUserList());
    }
    /**
     * Creates a new User Object with the right input of Username. returns true or false based on if
     * The User was successfully created or not. Cannot create a speaker unless an Organiser is
     * currently Logged in
     */
    public boolean createUser(){
        System.out.println("Enter Username of new User");
        String username = scanner.nextLine();
        System.out.println("Enter Password for new User");
        String password = scanner.nextLine();
        System.out.println("Enter The type of the User you want to create\n 1. Organiser \n 2. Attendee \n 3.Speaker");
        int userType = scanner.nextInt();
        switch(userType){
            case 1:
                if (userManager.createAccount(username, password, "Organiser")){
                    System.out.println("User successfully created");
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
            case 2:
                if (userManager.createAccount(username, password, "Attendee")){
                    System.out.println("User successfully created");
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
            case 3:
                if (validateNotLoggedIn() || userManager.getUserByID(currentUserId).getType() != 'O'){
                    System.out.println("You need to be logged in as an Organiser to do this");
                    return false;
                }
                User user = userManager.getUserByID(currentUserId);
                if (userManager.createAccount(username, password, "Speaker")){
                    System.out.println("User successfully created");
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
                }
        System.out.println("That is not a valid option for type");
        return false;
    }
    /**
     * Returns true or false based on whether a valid user is currently logged into the controller
     */
    public boolean validateNotLoggedIn(){
        return userManager.getUserByID(currentUserId) == null;
    }
    /**
     * Sends a Friend request from the User Using this controller to the a user whose ID is entered
     */
    public void sendFriendRequest(){
        if (validateNotLoggedIn()){
            System.out.println("You need to be logged in to do this");
        }
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
    /**
     * Accepts a Friend request from the Current user's List of friend requests.
     */
    public void acceptFriendRequest(){
        if (validateNotLoggedIn()){
            System.out.println("You need to be logged in to do this");}
        System.out.println("Enter Username of friend whose request you would like to accept");
        int potentialFriendId = validateUserIDInput();
        if (!userManager.acceptFriendRequest(currentUserId, potentialFriendId)){
            System.out.println("This person has not sent you a request");
        }
        else{
            System.out.println("You have now added each other as friends");
        }
    }

    /**
     * Logs out current user
     */
    public void  logOut() {
        currentUserId = -1;
    }

    /**
     * Returns the char type associated with the current user
     * @return getType() of current user
     */
    public char getUserType() {
        if (currentUserId != -1)
            return userManager.getUserByID(currentUserId).getType();
        else
            return 'N';
    }

    /**
     * Returns the list containing all Speaker
     * @return A LinkedList with all Speakers
     */
    public LinkedList<User> getSpeakerList() {
        return userManager.getSpeakerList();
    }

}
