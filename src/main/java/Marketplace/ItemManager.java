package Marketplace;

import Marketplace.Items.creator.ItemCreator;
import Marketplace.Items.types.Animal;
import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ItemManager {

    /**
     * Creates the items to be sold and sends to Database. Removes items sold from Database
     *
     */


//TODO: finish this and check with Claire for User_Controls
    public void create_post(String name, String description, Double price, String contact,
                            String password, String email, Item.campus campus, String itemtype){

        /**
         * Creates Item depending on the type given and store into Database
         */

        if (Objects.equals(itemtype, "animal")){
            ItemCreator itemCreator = new ItemCreator();
            Animal item = (Animal) itemCreator.makeItem(ItemCategories.animal);
            item.edit(name, description, contact, email, password, price, campus);
            Database.StoreItem(item);
        }

    }

//    public void store_post(ItemCategories category){
//        /**
//         * Stores the item into Database
//         */
//        ItemCreator itemCreator = new ItemCreator();
//        itemCreator.makeItem(category);
//        Item item = new Item();
//        Database.StoreItem(item);
//    }



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
