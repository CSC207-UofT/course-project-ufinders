package Marketplace;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import Main.*;
import Marketplace.comparators.HighPrice;
import Marketplace.comparators.LowPrice;
import Marketplace.filters.*;

public class User_Controls {

    /**
     * Prompts the user to choose a section of the marketplace to enter
     * and directs them there.
     *
     */
    public static void intro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to buy, sell, or exit the marketplace? (Write 'buy,' 'sell,' or 'exit')");
        String segmentchoice = input.nextLine();
        if (Objects.equals(segmentchoice, "buy")){
            buying_info();
        }
        else if (Objects.equals(segmentchoice, "sell")){
            selling_info();
        }
        else if (Objects.equals(segmentchoice, "exit")){
            MainController.menu();
        }
        else {
            intro();
        }
    }

    /**
     * Gets information on what item the user is selling and passes to Sell_Buy.
     *
     *
     */
    private static void selling_info(){
        //need to edit this to have types as well
        String name = get_input("What is the name of the item you're selling?");
        String description = get_input("What is the description of the item you're selling?");
        String price = get_input("What is the price of the item you're selling?");
        String contact = get_input("What is your contact information?");
        String email = get_input("What is your email?");
        String password = get_input("What password would you like to use to delete this post after the item is sold?");
        Double price1 = Double.parseDouble(price);
        new ItemManager().create_post(name, description, price1, contact, password, email);
        System.out.println("Your post has been created!");

    }

    /**
     * Gets information on what item the user is searching for.
     *
     */
    private static void buying_info(){
        Searcher search = new Searcher();
        ItemCategories type = ItemCategories.misc;
        if (get_input("Do you want to search for a specific type of item? (Y/N)").equals("Y")){
            String typechoice = get_input("GIANT LONG PROMPT");
            if (typechoice.equals("Textbook")){
                type = ItemCategories.textbook;
            }
            if (typechoice.equals("Clothes")){
                type = ItemCategories.clothes;
            }
            if (typechoice.equals("Animal")){
                type = ItemCategories.animal;
            }
            search.addFilter(new typeFilter(type));
        }

        search.addFilter((get_input("Please enter a keyword for your search (ex. computer, desk, biology):")));

        String filteranswer = get_input(get_prompt(type));
        while (!filteranswer.equals("Done")){
            if (filteranswer.equals("Campus")){
                //filter by campus
            }
            if (filteranswer.equals("Price")){
                //filter by price
            }
            if (filteranswer.equals("Condition")){
                //filter by condition
            }
            filteranswer = get_input(get_prompt(type));
        }


        //figure out the sorting stuff pls
        String sortchoice = get_input("do you want to sort by price or time posted? (price/time)");
        if (Objects.equals(sortchoice, "price")){
            String sortkey = get_input("do you want to sort by high to low or low to high? (high-low/low-high)");
            if (sortkey.equals("high-low")){
                search.addSorter(new Sorter(new HighPrice()));
            }
            else if (sortkey.equals("low-high")){
                search.addSorter(new Sorter(new LowPrice()));
            }
        }
        else if (Objects.equals(sortchoice, "time")){
            String sortkey = get_input("do you want to sort by most recent or oldest? (recent/oldest)");
            if (sortkey.equals("recent")){
                search.setSortchoice("low-high", "time");
            }
            if (sortkey.equals("oldest")){
                search.setSortchoice("high-low", "time");
            }
        }
        System.out.println("Loading your search");
        search.execute();
    }

    /**
     * Gets information on what filters the searcher wants.
     *
     */
    private static String get_prompt(ItemCategories type){
        String prompt = "INSERT LONG STRING HERE";
        if (type = ItemCategories.electronics){
            prompt+= "INSERT MORE HERE";
        }
        if (type = ItemCategories.animal){
            prompt+= "INSERT MORE HERE";
        }
        if (type = ItemCategories.clothes){
            prompt+= "INSERT MORE HERE";
        }
        if (type = ItemCategories.textbook){
            prompt+= "INSERT MORE HERE";
        }
        return prompt;
    }

    /**
     * Prints a prompt to the terminal and returns what the user writes in response.
     *
     * @param prompt prompt to be printed for user
     */
    private static String get_input(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
        //  if (possibleinput.contains(response)){
        //      return response;
        //   }
        //  else{
        //      get_input(prompt, possibleinput);
        //
        //could edit this so that it checks whether the answer is acceptable and forces user to give another line if it's not?
    }
}
