package Marketplace;

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

    @Override
    public String toString(){
        String s = "";
        for (Item item: item_type_lst){
            s += "NAME OF ITEM: " + item.getName() + "\nITEM DESCRIPTION: " + item.getItem_description();
            s += "\n";
        }
        return s;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Item item1 = new Item("cats", "furry", "123", "123", "123", 123);
        Item item2 = new Item("dogs", "cute", "123", "123", "123", 123);
        StoreItem(item1);
        StoreItem(item2);
//        System.out.println("before getlst \n" + d + "end");
        GetLst();
        Database d = new Database();
        System.out.println("after getlst\n" + d + "end");
//        System.out.println(GetLst().toString());

    }


}

