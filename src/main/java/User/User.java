package User;



public class User {
    public String UserID;
    public String Password;
    public String Directory;


    /**
     * Constructs a USER
     *
     * @param id The user id of the user
     * @param password The name of the user
     * @param directory The directory of the user
     */
    public User(String id, String password, String directory){
        this.setUserID(id);
        this.setPassword(password);
        this.setdirectory(directory);
    }


    /**
     * Sets id for the user
     *
     * @param id The user's id
     */
    public void setUserID(String id){
        UserID = id;
    }

    /**
     * Sets name for user
     *
     * @param password The user's password
     */
    public void setPassword(String password){
        Password = password;
    }

    /**
     * Sets directory for user
     *
     * @param directory The user's directory
     */
    public void setdirectory(String directory){
        Directory = directory;
    }
}
