package User;

public abstract class User {
    private int userID;
    private String username;
    private String password;

    /**
     * Constructs a new User object
     * @param userID: A number unique to each user, is an integer
     * @param username: a name unique to this instance of user, is a String
     * @param password: a password used to log in, is a String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // user_ID needs to be unique so it needs to be assigned to user by something above like a use case
    // which can access all the usernames. Since use cases can edit entity info they can assign
    // attribute
    /**
     * Returns the username of this User.
     * @return username
     */
    public String get_username(){
        return this.username;
    }
    /**
     * Returns the password of this User.
     * @return username
     */
    public String get_password() {
        return this.password;
    }


}


