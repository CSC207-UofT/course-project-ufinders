package Marketplace;

import Marketplace.Items.types.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Results {

    /**
     * Displays results of search to the user.
     *
     */


    /**
     * Prints and presents list of items to the user.
     *
     * @param items ArrayList of items to be iterated over.
     */

    public static void present(ArrayList<Item> items){
        Scanner input = new Scanner(System.in);
        printlist(items);
        System.out.println("Would you like to view one of the items? If so, please type the corresponding number on its left. To exit, type 0.");
        int view_item = input.nextInt();
        while (view_item != 0){
            item_present(items.get(view_item - 1));
            System.out.println("Would you like to view another item? If so, please type the corresponding number on its left. To exit, type 0.");
            view_item = input.nextInt();
        }
    }

    public static void item_present(Item item){
        System.out.println(item.getName());
        System.out.println("$" + item.getPrice());
        System.out.println(item.getItem_description());

        if (item instanceof Animal){
            System.out.println(((Animal) item).getAnimal_type());
        }
        if (item instanceof Clothes){
            System.out.println("Size: " + ((Clothes) item).getSize());
            System.out.println(item.getCondition());
        }
        if (item instanceof Electronic){
            System.out.println("Tech Specifications: " + ((Electronic) item).getTech_specifications());
        }
        if (item instanceof Textbook){
            System.out.println("Course: " + ((Textbook) item).getCourse());
        }

        System.out.println("On the " + item.getCampus() + " campus.");
        System.out.println(item.getContact_email());
        System.out.println(item.getContact_num());
    }

    public static void printlist(ArrayList<Item> items){
        int curr_item = 0;
        while(curr_item < items.size()){
            System.out.println((curr_item + 1) + ". " + items.get(curr_item).getName() + ", for $" + items.get(curr_item).getPrice());
        }
    }
}
