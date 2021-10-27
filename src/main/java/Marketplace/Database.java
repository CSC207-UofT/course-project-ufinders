package Marketplace;

import java.util.ArrayList;

public class Database {
    /**
     * A database that stores a list of items from ItemManager
     *
     *
     * @param item_lst the list of items that are posts
     *
     */

    private static ArrayList<Item> item_type_lst = new ArrayList<>();


    public static void StoreItem(Item item) {
        /**
         * Adds the post to the item_lst
         */

        item_type_lst.add(item);

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

