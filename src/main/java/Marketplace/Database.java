
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


//    private static ArrayList<HashMap<String, Object>> item_lst = new ArrayList<>();
    private static ArrayList<Item> item_type_lst = new ArrayList<Item>();

//    public Database(){
//        /**
//         * The constructor for Database and initializes an empty array list
//         * */
//        item_lst = new ArrayList<HashMap<String, Object>>();
//
//    }


    public static void StoreItem(Item item) {
        /**
         * Adds the post to the item_lst
         */

        item_type_lst.add(item);
        System.out.println(item_type_lst);

    }

    public static ArrayList<Item> GetLst(){
        /**
         * Returns the item_lst
         */
        return item_type_lst;
    }
    public static void main(String[] args) {

        System.out.println(item_type_lst);
    }

}

