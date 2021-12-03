package Marketplace;

import Marketplace.Items.ItemCreator;
import Marketplace.Items.types.Animal;
import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Electronic;
import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;
import Marketplace.Items.types.Misc;
import Marketplace.Items.types.Textbook;

import java.util.ArrayList;


public class ItemManager {

    /**
     * Creates the items to be sold and sends to Database. Removes items sold from Database
     *
     */

    public ItemManager(){}

    /**
     * Creates Animal Item and store into Database
     */
    public void CreatePostAnimal(ArrayList<Object> info){

        ItemCreator itemCreator = new ItemCreator();
        Animal item = (Animal) itemCreator.makeItem(ItemCategories.animal);
        String title = (String) info.get(1);
        String description = (String) info.get(2);
        double price = (double) info.get(3);
        String contact = (String) info.get(4);
        String password = (String) info.get(6);
        String email = (String) info.get(5);
        Item.campus campus = (Item.campus) info.get(7);
        String animal = (String) info.get(8);
        item.edit(title, description, contact, email, password, price, campus, animal);
        Database.StoreItem(item);
    }

    /**
     * Creates Clothes Item and store into Database
     */
    public void CreatePostClothes(ArrayList<Object> info){

        ItemCreator itemCreator = new ItemCreator();
        Clothes item = (Clothes) itemCreator.makeItem(ItemCategories.clothes);
        String title = (String) info.get(1);
        String description = (String) info.get(2);
        double price = (double) info.get(3);
        String contact = (String) info.get(4);
        String password = (String) info.get(6);
        String email = (String) info.get(5);
        Item.campus campus = (Item.campus) info.get(7);
        Item.condition condition = (Clothes.condition) info.get(8);
        Clothes.size size = (Clothes.size) info.get(9);
        item.edit(title, description, contact, email, password, price, campus, size, condition);
        Database.StoreItem(item);
    }

    /**
     * Creates Electronic Item and store into Database
     */
    public void CreatePostElectronic(String name, String description, double price, String contact, String email,
                                     String password, Item.campus campus, Item.condition condition,
                                     String tech_specifications){

        ItemCreator itemCreator = new ItemCreator();
        Electronic item = (Electronic) itemCreator.makeItem(ItemCategories.electronics);
        item.edit(name, description, contact, email, password, price, campus, condition, tech_specifications);
        Database.StoreItem(item);
    }

    /**
     * Creates Miscellaneous Item and store into Database
     */
    public void CreatePostMisc(String name, String description, double price, String contact, String email,
                               String password, Item.campus campus){

        ItemCreator itemCreator = new ItemCreator();
        Misc item = (Misc) itemCreator.makeItem(ItemCategories.misc);
        item.edit(name, description, contact, email, password, price, campus);
        Database.StoreItem(item);
    }

    /**
     * Creates Textbook Item and store into Database
     */
    public void CreatePostTextbook(String name, String description, double price, String contact, String email,
                                   String password, Item.campus campus, String course) {

        ItemCreator itemCreator = new ItemCreator();
        Textbook item = (Textbook) itemCreator.makeItem(ItemCategories.textbook);
        item.edit(name, description, contact, email, password, price, campus, course);
        Database.StoreItem(item);

    }

    /**
     * Checks to see if the password given by
     * user matches with the item in Database
     */
    public static boolean password_match(String title, String password){

        ArrayList<Item> item_lst = Database.GetLst();

        for (Item item: item_lst){
            if (item.getName().equals(title) & item.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }


    /**
     * Removes the item sold from post and return true. Needs to check the password before the item can be removed.
     */
    public static boolean remove_post(String title, String password){

        ArrayList<Item> posts = Database.GetLst();

        for (Item item: posts){
            if (item.getName().equals(title) & password_match(title, password)){
                Database.DeleteItem(item);
                return true;
            }
        }
        return false;
    }


}
