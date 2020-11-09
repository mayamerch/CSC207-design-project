package UserPackage;

import java.util.ArrayList;

public class UserManager {

    private UserFactory factory;
    private ArrayList<User> user_list;

    /**
     * Creates a UserManager with an empty list of Users and a user factory
     */
    public UserManager() {
        user_list = new ArrayList<User>();
        factory = new UserFactory();
    }

    /**
     * Creates a New user based on the type of User specified then adds it to a list of
     * Users and assigns the new user an ID
     * @param new_username: The username of the new account, String
     * @param new_password : The password of the new account, String
     * @param usertype: a character specifying the type of user to be created
     */
    public void create_account(String new_username, String new_password, String usertype){
        User new_user;
        new_user = this.factory.getuser(new_username, new_password, usertype);
        // Casting as User?
        if (new_user != null) {
            user_list.add(new_user);
            int new_userID = user_list.size();
            new_user.set_userId(new_userID);
            }
    }
    /**
     * Takes an instance of User provided by the controller and a possible
     * username and password combination and check to see if they match
     * the username and password stored in the instance of user
     * @param username: The String entered as username of the account,
     * @param password : The String entered as the password of the account,
     * @param user: an instance of User the person is trying to log into
     */
    public boolean validate_login(User user, String username, String password){
        return ((user.get_username().equalsIgnoreCase(username)) && (user.get_password().equals(password)));
    }
    // should optimize this
    public boolean validate_id(int user_id){
        for (User user : user_list) {
            if (user.get_userID() == user_id) {
                return true;}
        }
        return false;}

    public  ArrayList<User> get_user_list(){
        return user_list;
    }
}