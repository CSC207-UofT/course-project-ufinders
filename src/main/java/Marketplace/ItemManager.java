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
        double price = (double) info.get(2);
        Item.campus campus = (Item.campus) info.get(6);
        item.edit(info.get(0), info.get(1), info.get(3), info.get(4), info.get(5), price, campus, info.get(7));
        Database.StoreItem(item);
    }

    /**
     * Creates Clothes Item and store into Database
     */
    public void CreatePostClothes(ArrayList<Object> info){

        ItemCreator itemCreator = new ItemCreator();
        Clothes item = (Clothes) itemCreator.makeItem(ItemCategories.clothes);
        double price = (double) info.get(2);
        Item.campus campus = (Item.campus) info.get(6);
        Item.condition condition = (Item.condition) info.get(7);
        Clothes.size size = (Clothes.size) info.get(8);
        item.edit(info.get(0), info.get(1), info.get(3), info.get(4), info.get(5), price, campus, size, condition);
        Database.StoreItem(item);
    }

    /**
     * Creates Electronic Item and store into Database
     */
    public void CreatePostElectronic(ArrayList<Object> info){

        ItemCreator itemCreator = new ItemCreator();
        Electronic item = (Electronic) itemCreator.makeItem(ItemCategories.electronics);
        double price = (double) info.get(2);
        Item.campus campus = (Item.campus) info.get(6);
        Item.condition condition = (Item.condition) info.get(7);
        item.edit(info.get(0), info.get(1), info.get(3), info.get(4), info.get(5), price, campus, condition,
                info.get(8));
        Database.StoreItem(item);
    }

    /**
     * Creates Miscellaneous Item and store into Database
     */
    public void CreatePostMisc(ArrayList<Object> info){

        ItemCreator itemCreator = new ItemCreator();
        Misc item = (Misc) itemCreator.makeItem(ItemCategories.misc);
        double price = (double) info.get(2);
        Item.campus campus = (Item.campus) info.get(6);
        item.edit(info.get(0), info.get(1), info.get(3), info.get(4), info.get(5), price, campus);
        Database.StoreItem(item);
    }

    /**
     * Creates Textbook Item and store into Database
     */
    public void CreatePostTextbook(ArrayList<Object> info) {

        ItemCreator itemCreator = new ItemCreator();
        Textbook item = (Textbook) itemCreator.makeItem(ItemCategories.textbook);
        double price = (double) info.get(2);
        Item.campus campus = (Item.campus) info.get(6);
        item.edit(info.get(0), info.get(1), info.get(3), info.get(4), info.get(5), price, campus, info.get(7));
        Database.StoreItem(item);

    }

    /**
     * Checks to see if the password given by
     * user matches with the item in Database
     */
    public static boolean password_match(String title, String password){

        for (Item item: Database.item_type_lst){
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

        for (Item item: Database.item_type_lst){
            if (item.getName().equals(title) & password_match(title, password)){
                int id = item.getId();
                Database.DeleteItem(id);
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] Args){
//        Animal animal = new Animal();
//        animal.edit("Apple", "35y/o", "123", "123", "123",
//                100, Item.campus.UTSG, "horse");
//        Electronic electronic = new Electronic();
//        electronic.edit("Banana", "product", "123", "123", "123",
//                1000, Item.campus.UTSC, Item.condition.LikeNew, "Phone");
//        Clothes clothes = new Clothes();
//        clothes.edit("Bright", "colorful", "123", "123", "123",
//                12, Item.campus.UTM, Clothes.size.S, Item.condition.New);
//        Database.StoreItem(animal);
//        Database.StoreItem(electronic);
//        Database.StoreItem(clothes);
//        remove_post("Apple", "123");
//        System.out.println(Database.GetLst());
//    }

}
