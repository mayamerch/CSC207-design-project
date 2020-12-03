package UserPackage;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private int currentUserID;
    protected UserGateway userGateway;
    protected UserManager userManager;
    protected UserPresenter userPresenter;
    Scanner scanner = new Scanner(System.in);


    /**
     * Constructs a new UserController object, setting the user manager based off of the gateway
     */
    public UserController() {
        this.currentUserID = -1;//by default no user is logged in
        this.userGateway = new UserGateway();
        //this.userPresenter = new UserPresenter();
        try {
            this.userManager = new UserManager(this.userGateway.readUserMap());
        } catch (NullPointerException n) {
            System.out.println("Empty UserManager List of Users");
            this.userManager = new UserManager();
            // Add something so if this fails it calls on the no argument constructor
        }
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
     * if logs in, sets currentUserId to the id of the user logged in
     * @return the type of the User that logged in, character
     */
    public boolean userLogin(String username, String password){
        //if you need the old version of userLogin that returned UserType, use getUserType() instead
        this.currentUserID = userManager.validateLogin(username, password);  //-1 if user does not log in successfully
        if (currentUserID >= 0)
            return true;    //returns true only if user logs in successfully
        return false;       //false if user gets password or username wrong
    }

    /**
     * Logs in the User based on the Username and Password entered by the User,
     * this specifically handles the user input
     * @return the type of the User that logged in, character
     */
    public boolean userLogin(){
        // pls work
        System.out.println("Press enter if there is no prompt directly following this line");
        scanner.nextLine();
        System.out.println("Enter Username");
        String username = scanner.nextLine();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        return userLogin(username, password);
    }


    /*public UserType userLogin(String username, String password) {
        int potentialID = userManager.validateLogin(username, password);
        if (potentialID >= 0 ) {
            currentUserId = potentialID;
            System.out.println("Login Successful");
            System.out.println("Your ID is " + currentUserId);
            return userManager.getUserByID(currentUserId).getType();
        } else {
            System.out.println("Invalid username or password");
            return null;
        }
    }*/

    public boolean checkUserVIP(int userID){
        return getUserManager().checkVIP(userID);
    }

    /**
     * Checks if a userID that is entered into the system belongs to an actual user.
     * @return the integer that the User entered
     */
    public int validateUserIDInput(){
        int userID;
        System.out.println("Enter ID of User");
        userID = scanner.nextInt();
        if (userID == currentUserID){
            System.out.println("You cannot put in your own ID/Username");
            return validateUserIDInput();
            }
        else if (userManager.getUserByID(userID) != null){
            return userID;}
        else{
            System.out.println("That is not a Valid UserID. Please try Again.");
            return validateUserIDInput();
        }
    }
    // This has to be a seperate function because you would validate the ID by converting
    // the username to a userId sucessfully
    /**
     * Checks if a usernamethat is entered into the system belongs to an actual user.
     * @return the username that the User entered, String
     */
    public String validateUsernameInput(){
        String username;
        User currentUser = getUserManager().getUserByID(currentUserID);
        String currentUsername = currentUser.getUsername();
        System.out.println("Enter Username of User");
        username = scanner.nextLine();
        if (username.equals(currentUsername)){
            System.out.println("You cannot enter your own username");
            return  validateUsernameInput();
        }
        else if(getUserManager().getUserIDByUsername(username) != -1){
            return username;
        }
        else{
            System.out.println("This is not a valid Username");
            return validateUsernameInput();
        }
    }
    /**
     * Accesses the gateway to save the List of Users contained within the UserManager attribute of this
     * Controller
     */
    public void saveUserList(){
        userGateway.saveUserMap(getUserManager().getUserMap());
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
        System.out.println("Enter The type of the User you want to create\n 1. Organizer \n 2. Attendee \n 3.Speaker");
        String userType = scanner.nextLine();
        switch(userType){
            case "1":
                if (userManager.createAccount(username, password, UserType.ORGANIZER)){
                    System.out.println("User successfully created");
                    userGateway.saveUserMap(userManager.getUserMap());
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
            case "2":
                if (validateNotLoggedIn() || userManager.getUserByID(currentUserID).getType() != UserType.ORGANIZER){
                    System.out.println("You need to be logged in as an Organizer to do this");
                    return false;
                }
                if (userManager.createAccount(username, password, UserType.ATTENDEE)){
                    System.out.println("User successfully created");
                    userGateway.saveUserMap(userManager.getUserMap());
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
            case "3":
                if (validateNotLoggedIn() || userManager.getUserByID(currentUserID).getType() != UserType.ORGANIZER){
                    System.out.println("You need to be logged in as an Organizer to do this");
                    return false;
                }
                if (userManager.createAccount(username, password, UserType.SPEAKER)){
                    System.out.println("User successfully created");
                    userGateway.saveUserMap(userManager.getUserMap());
                    return true;
                }
                else{System.out.println("The Username must be unique.");
                return false;}
                }
        System.out.println("That is not a valid option for type");
        return false;
    }
    public boolean createUser(String username, String password, UserType userType){
        return userManager.createAccount(username, password, userType);
    }
    /**
     * Returns true or false based on whether a valid user is currently logged into the controller
     */
    public boolean validateNotLoggedIn(){
        return userManager.getUserByID(currentUserID) == null;
    }

    public int getCurrentUserId(){
        return currentUserID;
    }

    public boolean sendFriendRequest(String friendUsername){
        return userManager.sendFriendRequest(currentUserID, friendUsername);
    }
    public boolean sendFriendRequest(int friendID){
        return userManager.sendFriendRequest(currentUserID, friendID);
    }
    /**
     * Sends a Friend request from the User Using this controller to the a user whose ID is entered
     */
    public boolean sendFriendRequest() {
        if (validateNotLoggedIn()) {
            System.out.println("You need to be logged in to do this");
            return false;
        }
        System.out.println("Add By Username (press 1) or ID (Press another key)?");
        int userChoice = scanner.nextInt();
        if (userChoice == 1) {
            String currentUsername = getUserManager().getUserByID(currentUserID).getUsername();
            System.out.println("Enter Username of friend you would like to add");
            String potentialFriendUsername = validateUsernameInput();
            boolean friendRequest = userManager.sendFriendRequest(currentUsername, potentialFriendUsername);
            if (!friendRequest) {
                System.out.println("You have either already sent a friend request or " +
                        "they have already accepted you as a friend or you entered your username");
                return false;
            } else {
                System.out.println("Friend Request Sent");
                return true;
            }
        }
        else {
            System.out.println("Enter ID of friend you would like to add");
            int potentialFriendId = validateUserIDInput();
            boolean friendRequest = userManager.sendFriendRequest(currentUserID, potentialFriendId);
            if (!friendRequest) {
                System.out.println("You have either already sent a friend request or " +
                        "they have already accepted you as a friend or you entered your username");
                return false;
            } else {
                System.out.println("Friend Request Sent");
                return true;
            }
        }
    }

    public boolean acceptFriendRequest(String friendUsername){
        return userManager.acceptFriendRequest(currentUserID, friendUsername);
    }
    public boolean acceptFriendRequest(int friendID){
        return userManager.acceptFriendRequest(currentUserID, friendID);
    }

    /**
     * Accepts a Friend request from the Current user's List of friend requests.
     */
    public boolean acceptFriendRequest(){
        if (validateNotLoggedIn()){
            System.out.println("You need to be logged in to do this");
            return false;}
        System.out.println("Add By Username (press 1) or ID (Press another key)?");
        int userChoice = scanner.nextInt();
        if (userChoice == 1){
            String currentUsername = getUserManager().getUserByID(currentUserID).getUsername();
            System.out.println("Enter Username of friend whose request you would like to accept");
            String potentialFriendUsername = validateUsernameInput();
            if (!userManager.acceptFriendRequest(currentUsername, potentialFriendUsername)){
                System.out.println("This person has not sent you a request");
                return false;
            }
            else{
                System.out.println("You have now added each other as friends");
                return true;
            }
        }
        else{
            System.out.println("Enter ID of friend whose request you would like to accept");
            int potentialFriendId = validateUserIDInput();
            if (!userManager.acceptFriendRequest(currentUserID, potentialFriendId)){
                System.out.println("This person has not sent you a request");
                return false;
            }
            else{
                System.out.println("You have now added each other as friends");
                return true;
            }
        }
    }

    /**
     * Logs out current user
     */
    public void  logOut() {
        currentUserID = -1;
        System.out.println("You have been logged out");
    }

    /**
     * Returns the char type associated with the current user
     * @return getType() of current user
     */
    public UserType getUserType() {
        if (currentUserID >= 0)
            return userManager.getUserByID(currentUserID).getType();
        else
            return null;
    }
    /**
     * Returns the ID of current user if logged in
     * @return true or false based on logged in or not
     */
    public boolean printYourID(){
        if (!validateNotLoggedIn()){
            System.out.println(currentUserID);
            return true;
        }
        return false;
    }

    /**
     * Returns the list containing all Speaker
     * @return A LinkedList with all Speakers
     */
    public LinkedList<User> getSpeakerList() {
        return userManager.getSpeakerList();
    }

    public void setUserVIP(){
        System.out.println("Enter the ID of user you want to add");
        int userID = validateUserIDInput();
        System.out.println("Enter the new VIP status of this User \n 1. VIP = True \n 2. VIP = False");
        int userChoice = scanner.nextInt();
        getUserManager().changeVIP(userID, userChoice == 1);
        // 1 to set to true, anything else to set to false
    }

    //gets friends list of logged on user, might need to account for no logged on user
    public List<Integer> getFriendsList(){
        return userManager.getUserByID(currentUserID).getFriendsList();
    }
    public List<Integer> getFriendRequestList(){
        return userManager.getUserByID(currentUserID).getFriendRequestList();
    }

    /**
     * Interacts with user and asks for input for which user related action the user would like to take then performs
     * actions related to events based on that input.
     * @param currentUserId The id of the user its interacting with
     **/
    /*public void run(int currentUserId){
        Scanner reader = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);

        up.printMenu();
        String UserInput = reader2.nextLine();

        while(!UserInput.equals("0")){
            switch (UserInput){
                case "1":
                    up.printList(userManager.getUserByID(currentUserId).getFriendsList());
                    UserInput="0";
                    break;
                case "2":
                    up.printList(userManager.getUserByID(currentUserId).getFriendRequestList());
                    UserInput="0";
                    break;
                default:
                    System.out.println("Try again");
                    up.printMenu();
                    break;
            }
        }
    }*/

}
