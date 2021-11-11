package Marketplace;

import Marketplace.Items.creator.ItemCreator;
import Marketplace.Items.types.*;

import java.util.ArrayList;
import java.util.Objects;


public class ItemManager {

    /**
     * Creates the items to be sold and sends to Database. Removes items sold from Database
     *
     */

    public void CreatePostAnimal(String name, String description, double price, String contact,
                                 String password, String email, Item.campus campus, String animal_type){
        /**
         * Creates Animal Item and store into Database
         */
        ItemCreator itemCreator = new ItemCreator();
        Animal item = (Animal) itemCreator.makeItem(ItemCategories.animal);
        item.edit(name, description, contact, email, password, price, campus, animal_type);
        Database.StoreItem(item);
    }

    public void CreatePostClothes(String name, String description, double price, String contact,
                                  String password, String email, Item.campus campus, Item.size size,
                                  Item.condition condition){
        /**
         * Creates Clothes Item and store into Database
         */
        ItemCreator itemCreator = new ItemCreator();
        Clothes item = (Clothes) itemCreator.makeItem(ItemCategories.clothes);
        item.edit(name, description, contact, email, password, price, campus, size, condition);
        Database.StoreItem(item);
    }

    public void CreatePostElectronic(String name, String description, double price, String contact, String email,
                                     String password, Item.campus campus, Item.condition condition,
                                     String tech_specifications){
        /**
         * Creates Electronic Item and store into Database
         */
        ItemCreator itemCreator = new ItemCreator();
        Electronic item = (Electronic) itemCreator.makeItem(ItemCategories.electronics);
        item.edit(name, description, contact, email, password, price, campus, condition, tech_specifications);
        Database.StoreItem(item);
    }

    public void CreatePostMisc(String name, String description, double price, String contact, String email,
                               String password, Item.campus campus){
        /**
         * Creates Miscellaneous Item and store into Database
         */
        ItemCreator itemCreator = new ItemCreator();
        Misc item = (Misc) itemCreator.makeItem(ItemCategories.misc);
        item.edit(name, description, contact, email, password, price, campus);
        Database.StoreItem(item);
    }

    public void CreatePostTextbook(String name, String description, double price, String contact, String email,
                                   String password, Item.campus campus, String course) {
        /**
         * Creates Textbook Item and store into Database
         */
        ItemCreator itemCreator = new ItemCreator();
        Textbook item = (Textbook) itemCreator.makeItem(ItemCategories.textbook);
        item.edit(name, description, contact, email, password, price, campus, course);
        Database.StoreItem(item);

    }


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
