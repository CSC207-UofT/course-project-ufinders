package Marketplace;

import Marketplace.Items.types.Item;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable{
    /**
     * A database that stores a list of items from ItemManager
     *
     *
     * @param item_lst the list of items that are posts
     *
     */

    private static ArrayList<Item> item_type_lst = new ArrayList<>();


    public static void StoreItem(Item item){
        /**
         * Adds the post to the item_lst
         */

        item_type_lst.add(item);
        try {
            SerializeItem();
        } catch (Exception e){
            System.out.println("Cannot store item.");
        }

    }

    private static void SerializeItem() throws IOException {
        /**
         * Serializes the item into a file called itemlst.txt
         */

        FileOutputStream newfile = new FileOutputStream("itemlst.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(newfile);
        outputStream.writeObject(item_type_lst);

    }

    private static void DeserializeItem() throws IOException, ClassNotFoundException {
        /**
         * Deserializes the items in the file
         */

        FileInputStream retrievedfile = new FileInputStream("itemlst.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(retrievedfile);
        item_type_lst = (ArrayList<Item>) objectInputStream.readObject();

    }

    public static ArrayList<Item> GetLst() {
        /**
         * Returns the item_lst
         */
        try{
            DeserializeItem();
        } catch (Exception e){
            System.out.println("Item cannot be retrieved");
        }
        return item_type_lst;
    }

}

