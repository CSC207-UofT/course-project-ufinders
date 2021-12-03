package Marketplace;

import Marketplace.Items.types.Item;

import java.io.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database implements Serializable{
    /**
     * A database that stores a list of items from ItemManager
     *
     *
     *
     */

    public static ArrayList<Item> item_type_lst = new ArrayList<>();

    /**
     * Adds the post to the item_lst
     */
    public static void StoreItem(Item item){

        item_type_lst.add(item);
        try {
            SerializeItem();
        } catch (Exception e){
            System.out.println("Cannot store item.");
        }

    }


    /**
     * Serializes the item into a file called itemlst.txt
     */
    private static void SerializeItem() throws IOException {

        FileOutputStream newfile = new FileOutputStream("itemlst.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(newfile);
        outputStream.writeObject(item_type_lst);
        newfile.close();

    }


    /**
     * Deserializes the items in the file
     */
    private static void DeserializeItem() throws IOException, ClassNotFoundException {

        FileInputStream retrievedfile = new FileInputStream("itemlst.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(retrievedfile);
        item_type_lst = (ArrayList<Item>) objectInputStream.readObject();
        retrievedfile.close();
    }

    /**
     * Returns the item_lst
     */
    public static ArrayList<Item> GetLst() {

        try{
            DeserializeItem();
        } catch (Exception e){
            System.out.println("Item cannot be retrieved");
        }
        return item_type_lst;
    }

    /**
     * Deletes item from list
     */
    public static void DeleteItem(int id){
        int i = 0;
        while (i <= id){
            if (i == id){
                Item item = item_type_lst.get(i);
                item_type_lst.remove(item);
            }
            i++;
        }
        try{
            FileOutputStream newfile = new FileOutputStream("itemlst.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(newfile);
            outputStream.writeObject(item_type_lst);
        }
        catch (Exception e){
            System.out.println("Cannot store item.");
        }
    }



}

