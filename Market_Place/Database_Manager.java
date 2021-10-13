import java.util.HashMap;
import java.util.Scanner;


public class Database_Manager {

    /**
     * Manages posts
     * @param post a post with information given by user
     *
     */

    public HashMap<String, String> post;

    public Database_Manager(){
        /**
         * The constructor for Database_Manager
         */
        this.post = new HashMap<>();
    }

//    Not sure if this will work (the input part)
    public HashMap<String, String> create_post(){

        /**
        * Creates the post through user's input of info and returns
         * list of info of post
         */
        Scanner title = new Scanner(System.in);
        System.out.println("Enter title of item");
        String item_title = title.nextLine();
        this.post.put("title", item_title);
        Scanner description = new Scanner(System.in);
        System.out.println("Enter description of item");
        String item_descript = description.nextLine();
        this.post.put("description", item_descript);
        Scanner pass = new Scanner(System.in);
        System.out.println("Create password for item");
        String item_password = pass.nextLine();
        this.post.put("password", item_password);


        return post;
    }

/*
    public String searched_item(String title){
        /**
         * Search through list of items and  * /
    }
    Don't know if we actually need this in this class since I am creating a post here in
    Unless I am doing something wrong with this class, I am not too sure what to do with this method


*/

    public static boolean password_match(){
        /**
         * Checks to see if the password given by
         * user matches
         */
        Scanner pass = new Scanner(System.in);
        System.out.println("Enter the password created for this item");
        String item_pass = pass.nextLine();
        this.post.put("title", item_pass);
        String password = post.get("password");
        return item_pass.equals(password);

    }

    private void remove_post(){
        /**
         * If the password_match is true, the it will remove post from
         * database
         * */
        if (password_match()){

        }
    }
}
