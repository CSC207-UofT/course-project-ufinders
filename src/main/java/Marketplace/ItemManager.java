package Marketplace;

import java.util.ArrayList;
import java.util.HashMap;


public class ItemManager {

    /**
     * Creates the items to be sold and removes items sold
     * @param post a post with information given by user
     *
     */
    public HashMap<String, Object> post;

    public ItemManager(){
       /**
        * Initiates post into a hashmap
        */
        this.post = new HashMap<>();
    }

//    TODO: make something like a ShapeFactory in create_post or make another method

    public void create_post(String name, String description, Double price, String contact,
                                   String password, String email){

        /**
         * Creates Item from Item and stores into Database.
         */

        this.post.put("name",name);
        this.post.put("description", description);
        this.post.put("password", password);
        this.post.put("price",price);
        this.post.put("contact",contact);
        this.post.put("email", email);
        Item item = new Item(name, description, contact, email, password, price);
        Database.StoreItem(item);
    }


//    TODO: create an ID for Item

    public static boolean password_match(String title, String password){
        /**
         * Checks to see if the password given by
         * user matches with the item in Database
         */
        ArrayList<Item> item_lst = Database.GetLst();

        for (Item item: item_lst){
            if (item.getName().equals(title) & item.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }

    public static boolean remove_post(String title, String password){
        /**
         * Removes the item sold from post and return true. Needs to check the password before the item can be removed.
         */
        ArrayList<Item> posts = Database.GetLst();

        for (Item item: posts){
            if (item.getName().equals(title) & password_match(title, password)){
                posts.remove(item);
                return true;
            }
        }
        return false;
    }




}
