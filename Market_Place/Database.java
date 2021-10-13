import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    /**
     * A database that stores a list of items from Database_Manager
     *
     *
     * @param item_lst the list of items that are posts
     *
     */

//    we need to create an id that makes the items in the list unique


    public static ArrayList<HashMap<String, String>> item_lst;

    public Database(HashMap<String, String> post){
        /**
         * The constructor for Database and initializes an empty array list
         * */
        item_lst = new ArrayList<HashMap<String, String>>();
        item_lst.add(post);
    }


}

