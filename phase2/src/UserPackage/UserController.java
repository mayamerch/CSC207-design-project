package UserPackage;

import java.util.ArrayList;
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
     * Constructs a new UserController object based on an existing UserManager. For testing
     * @param userManager the UserManager that the controller will use
     */
    public UserController(UserManager userManager){
        this.currentUserID = -1;
        this.userManager = userManager;
        this.userGateway = new UserGateway();
    }

    /**
     * Returns the userManager attribute of the User Controller
     * @return userManager, the UserManager Attribute of the controller
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Logs in the User based on the Username and Password entered by the User
     * if logs in, sets currentUserId to the id of the user logged in
     * @param username the proposed username of the user, String
     * @param password  the proposed password of the user, String
     * @return true if the user logged in successfully
     */
    public boolean userLogin(String username, String password){
        //if you need the old version of userLogin that returned UserType, use getUserType() instead
        this.currentUserID = userManager.validateLogin(username, password);  //-1 if user does not log in successfully
        return currentUserID >= 0;    //returns true only if user logs in successfully
        //false if user gets password or username wrong
    }

    /**
     * Logs in the User based on the Username and Password entered by the User,
     * this specifically handles the user input
     * @return true or false based on sucessful login, boolean
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

    /**
     * Takes in an UserID and checks the VIP status
     * @param userID the ID of the user whose status will be checked, int
     * @return true if user is VIP
     */
    public boolean checkUserVIP(int userID){
        return getUserManager().checkVIP(userID);
    }
    /**
     * Takes in an UserID and changes the VIP status to newVIPStatus
     * @param userID: ID of the user we want to change status of, int
     * @param newVIPStatus the new VIP status, boolean
     * @return true if the VIP status was changed, false if the status was already newVIPStatus
     */
    public boolean changeUserVIP(int userID, boolean newVIPStatus){
        if (getUserType() != UserType.ORGANIZER){
            return false;
        }
        return userManager.changeVIP(userID, newVIPStatus);
    }
    /**
     * Takes in an UserName rather than ID and changes the VIP status to newVIPStatus
     * @param username: username of the user we want to change status of, String
     * @param newVIPStatus the new VIP status, boolean
     * @return true if the VIP status was changed, false if the status was already newVIPStatus
     */
    public boolean changeUserVIP(String username, boolean newVIPStatus){
        int userID = userManager.getUserIDByUsername(username);
        return userManager.changeVIP(userID, newVIPStatus);
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
     * Checks if a username that is entered into the system belongs to an actual user.
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
    public void saveUserMap(){
        userGateway.saveUserMap(getUserManager().getUserMap());
    }
    /**
     * Creates a new User Object with the right input of Username. returns true or false based on if
     * The User was successfully created or not. Cannot create a speaker unless an Organiser is
     * currently Logged in
     * @param username the username of the new User, String
     * @param password the pasword of the new user, String
     * @param userType the type of the new User, UserType object
     * @return  True if the user was successfully created, false if it was not created
     */
    public boolean createUser(String username, String password, UserType userType) {
        // userType provided by the presenter
        // No need to check login if creating Attendee
        if (userType == UserType.ATTENDEE) {
            return userManager.createAccount(username, password, UserType.ATTENDEE);
        }
        // shouldnt make account if not logged in
        if (validateNotLoggedIn()){return false;}
        UserType currentUserType = getUserType();
        // Speakers and Organisers can only be created by organisers
        if (currentUserType != UserType.ORGANIZER) {
            return false;
        }
        switch (userType) {
            case ORGANIZER:
                return userManager.createAccount(username, password, UserType.ORGANIZER);
            case SPEAKER:
                return userManager.createAccount(username, password, UserType.SPEAKER);
        }
        return false;
    }

    /**
     * Returns true or false based on whether a valid user is currently logged into the controller
     * @return  true if the user is not logged in while this controller is being used
     */
    public boolean validateNotLoggedIn(){
        return userManager.getUserByID(currentUserID) == null;
    }

    /**
     * Returns the ID of the user who is logged in and is using the UserController or -1 if not logged in
     * @return  the currentUserID attribute, int
     */
    public int getCurrentUserId(){
        return currentUserID;
    }
    /**
     * Calls on UserManager to send a friend request based on Username
     * @param friendUsername UserName of friend you send request to, String
     * @return true if the friend request was sent successfully, false if not
     */
    public boolean sendFriendRequest(String friendUsername){
        return userManager.sendFriendRequest(currentUserID, friendUsername);
    }

    /**
     * Calls on UserManager to send a friend request based on Username
     * @param friendID ID of friend you send request to, int
     * @return true if the friend request was sent successfully, false if not
     */
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
    /**
     * Accepts a Friend request from the Current user's List of friend requests based on username.
     * @param friendUsername : Username of friend whose request will be accepted, String
     * @return true when the friend request is successfully accepted.
     */
    public boolean acceptFriendRequest(String friendUsername){
        return userManager.acceptFriendRequest(currentUserID, friendUsername);
    }
    /**
     * Accepts a Friend request from the Current user's List of friend requests based on ID.
     * @param friendID : ID of friend whose request will be accepted, int
     * @return true when the friend request is successfully accepted.
     */
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
     * @return getType() of current user, UserType
     */
    public UserType getUserType() {
        if (currentUserID >= 0)
            return userManager.getUserByID(currentUserID).getType();
        else
            return null;
    }

    /**
     * Returns the list containing all Speakers from the UserManager
     * @return A LinkedList with all Speakers
     */
    public LinkedList<User> getSpeakerList() {
        return userManager.getSpeakerList();
    }

    //gets friends list of logged on user, might need to account for no logged on user
    /**
     * Interacts with UserManager to return a list of IDs which are the friends of the logged in User.
     * @return The list of Friend Ids, List of integers
     **/
    public List<Integer> getFriendsList(){
        return userManager.getUserByID(currentUserID).getFriendsList();
    }
    public List<Integer> getFriendRequestList(){
        return userManager.getUserByID(currentUserID).getFriendRequestList();
    }
    /**
     * Interacts with UserManager to return a list of speaker IDs from a list of Speakers.
     * @return The list of Speaker Ids, ArrayList
     **/
    public ArrayList<Integer> getSpeakerIds(){
        LinkedList<User> speakerList = userManager.getSpeakerList();
        ArrayList<Integer> speakerIds = new ArrayList<>();
        for (User user: speakerList) {
            speakerIds.add(user.getUserID());
        }
        return speakerIds;
    }
//    /**
//     * Interacts with UserManager to return a list of User IDs excluding friend requests and friends
//     * @return The list of User Ids that don't include friends or people who sent you a friend request
//     **/
//    public List<Integer> getNotFriendsNotRequests(){
//        if (validateNotLoggedIn()) {return null;}
//        List<Integer> friendRequestList = getFriendRequestList();
//        List<Integer> friendsList = getFriendsList();
//        List<Integer> bothList = new ArrayList<>();
//        bothList.addAll(friendsList);
//        bothList.addAll(friendRequestList);
//        bothList.add(currentUserID);
//        return userManager.excludeUsers(bothList);
//    }

    /**
     * Returns username of currently logged in user
     * @return Username of current userID of empty string if no one is logged in
     */
    public String getUsername() {
        if (currentUserID == 0)
            return "";
        User currUser = userManager.getUserByID(currentUserID);
        return currUser.getUsername();
    }

}
