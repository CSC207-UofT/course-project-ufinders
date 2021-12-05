package Marketplace;

import Marketplace.Items.ItemCreator;
import Marketplace.Items.types.Animal;
import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Electronic;
import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;
import Marketplace.Items.types.Misc;
import Marketplace.Items.types.Textbook;



public class ItemManager {

    /**
     * Creates the items to be sold and sends to Database. Removes items sold from Database
     *
     */

    public ItemManager(){}

    /**
     * Creates Animal Item and store into Database
     */
    public void CreatePostAnimal(String name, String description, double price, String contact,
                                 String password, String email, Item.campus campus, String animal_type){
        ItemCreator itemCreator = new ItemCreator();
        Animal item = (Animal) itemCreator.makeItem(ItemCategories.animal);
        item.edit(name, description, contact, email, password, price, campus, animal_type);
        Database.StoreItem(item);
    }

    /**
     * Creates Clothes Item and store into Database
     */
    public void CreatePostClothes(String name, String description, double price, String contact,
                                  String password, String email, Item.campus campus, Clothes.size size,
                                  Item.condition condition){
        ItemCreator itemCreator = new ItemCreator();
        Clothes item = (Clothes) itemCreator.makeItem(ItemCategories.clothes);
        item.edit(name, description, contact, email, password, price, campus, size, condition);
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

        for (Item item: Database.GetLst()){
            if (item.getName().equals(title) & item.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }


    /**
     * Removes the item sold from post and return true. Needs to check the password before the item can be removed.
     */
    public static boolean removePost(String title, String password){

        for (Item item: Database.GetLst()){
            if (item.getName().equals(title) & password_match(title, password)){
                int id = item.getId();
                Database.DeleteItem(id);
                return true;
            }
        }
        return false;
    }

}
