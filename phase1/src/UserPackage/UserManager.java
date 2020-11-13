package UserPackage;

import java.util.ArrayList;

public class UserManager {

    private UserFactory factory;
    private ArrayList<User> userList;

    /**
     * Creates a UserManager with an empty list of Users and a user factory
     */
    public UserManager() {
        userList = new ArrayList<>();
        factory = new UserFactory();
    }

    /**
     * Creates a New user based on the type of User specified then adds it to a list of
     * Users and assigns the new user an ID
     * @param newUsername: The username of the new account, String
     * @param newPassword : The password of the new account, String
     * @param usertype: a character specifying the type of user to be created
     */
    public boolean createAccount(String newUsername, String newPassword, String usertype){
        User new_user;
        new_user = this.factory.getuser(newUsername, newPassword, usertype);
        // Casting as User?
        if (new_user != null &&checkUnusedUsername(newUsername)) {
            userList.add(new_user);
            int new_userID = userList.size();
            new_user.set_userId(new_userID);
            return true;
        }
        return false;
    }
    private boolean checkUnusedUsername(String username){
        for (User x: userList){
            if (x.get_username()==username)
                return false;
        }
        return true;
    }
    /**
     * Takes an instance of User provided by the controller and a possible
     * username and password combination and check to see if they match
     * the username and password stored in the instance of user
     * @param username: The String entered as username of the account,
     * @param password : The String entered as the password of the account,
     * @param user: an instance of User the person is trying to log into
     */
    public boolean validateLogin(User user, String username, String password){
        return ((user.get_username().equalsIgnoreCase(username)) && (user.get_password().equals(password)));
    }
    public int validateLogin(String username, String password){
        for (User x : userList){
            if (validateLogin(x, username, password))
                return x.get_userID();
        }
        return -1;
    }

    public  ArrayList<User> get_user_list(){
        return userList;
    }

    /**
     * TODO: Change this to linked list implementation. Can replace validate ID with this function
     */
    public User getUserByID(int userID) {
        for (User user : userList) {
            if (userID == user.get_userID()) {
                return user;
            }
        }
        return null;
    }

        /**
         * TODO: MAy need to create separate lists for Attendee, Organiser and Speaker
         * AttendeeManager and OrganiserManager? Not very expandable
         */
        public boolean AddFriend(int attendeeId, int friendId){
            User user;
            user = getUserByID(attendeeId);
            User friend;
            friend = getUserByID(friendId);
            if (!(user instanceof Organiser || friend instanceof Organiser)){
                ((Attendee) user).add_friend(friend.get_username());
                return true;
            }
            else{
                return false;
            }

        }

}
