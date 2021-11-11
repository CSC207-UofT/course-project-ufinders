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
        campus campus = get_input("What is the campus you're selling the item from?");
        String typeentry = get_input("What is the type of the item you're selling? Your options are 'Animal' 'Clothing' 'Electronic' 'Textbook' or 'Other'");
        ItemCategories type;
        switch (typeentry) {
            case "Electronic":
                type = ItemCategories.electronics;
                condition condition = condition.valueOf(get_input("What is the condition of your electronic? " +
                        "Your options are: New, Used, LikeNew"));
            case "Animal":
                String animal_type = get_input("What type of animal are you selling?")
                break;
            case "Textbook":
                type = ItemCategories.textbook;
                String course = get_input("What is the condition of the textbook?");
                break;
            case "Clothing":
                type = ItemCategories.clothes;
                condition condition = condition.valueOf(get_input("What is the condition of the clothing? " +
                        "Your options are: New, Used, LikeNew"));
                size size = size.valueOf(get_input("What is the size of the clothing? " +
                        "Your options are: XS, S, M, L, XL"));
                break;
            case "Other":
                type = ItemCategories.misc;
                break;
        }
        new ItemManager().create_post(name, description, price1, contact, password, email);
        System.out.println("Your post has been created!");

    }

    /**
     * Gets information on what item the user is searching for.
     *
     */
    private static void buying_info(){
        Searcher search = new Searcher();
        ItemCategories type = Itemcategories.misc;
        if (get_input("Do you want to search for a specific type of item? (Y/N)").equals("Y")){
            String typechoice = get_input("If you would like to search only textbooks, type 'textbook'\nIf you " +
                    "would like to search only clothing, type 'clothes'\nIf you would like to search only electronics, " +
                    "type 'electronics'\nIf you would like to search only animals, type 'animal'\nIf you changed your mind, " +
                    "type 'done'");
            if (!typechoice.equals("done")) {
                    type = Itemcategories.valueOf(typechoice);
                    search.addFilter(new typeFilter(type));
            }
        }
        search.addFilter(new wordFilter((get_input("Please enter a keyword for your search (ex. computer" +
                ", desk, biology):"))));
        String filteranswer = get_input(get_prompt(type));
        while (!filteranswer.equals("Done")){
            switch (filteranswer) {
                case "Campus":
                    campus.valueOf(get_input("What campus do you want your item to be from? Options are: UTSG, UTSC, UTM"));
                    break;
                case "Price":
                    get_input("What is the lowest price you want to search for?");
                    get_input("What is the highest price you want to search for?");
                    break;
                case "Condition":
                    condition.valueOf(get_input("What condition do you want your item to be in? Options are: New, Used, LikeNew"));
                    break;
                case "Size":
                    size.valueOf(get_input("What size do you want your item to be? Options are: XS, S, M, L, XL"));
                    break;
                case "Course":
                    get_input("What course do you want your textbook to be for?");
                    break;
            }
        }
        String sortchoice = get_input("do you want to sort by price? (Y/N)");
        while (!sortchoice.equals("N")){
            if (Objects.equals(sortchoice, "Y")){
                String sortkey = get_input("do you want to sort by high to low or low to high? (high-low/low-high)");
                if (sortkey.equals("high-low")){
                    search.addSorter(new Sorter(new HighPrice()));
                }
                else if (sortkey.equals("low-high")){
                    search.addSorter(new Sorter(new LowPrice()));
                }
            }
            else{
                sortchoice = get_input("do you want to sort by price? (Y/N)");
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
        String prompt = "Here are your filter options:\nTo filter results by campus, type 'Campus'\nTo filter results by price, type 'Price'\n";
        if (type = ItemCategories.electronics){
            prompt+= "To filter results by condition, type 'condition'";
        }
        if (type = ItemCategories.clothes){
            prompt+= "To filter results by condition, type 'condition'\nTo filter results by size, type 'size'";
        }
        if (type = ItemCategories.textbook){
            prompt+= "To filter results by course, type 'Course'";
        }
        prompt+= "You can add multiple filters, but please only select one option for each category (ex. only choose one campus, don't go back to choose a second one)\n" +
                "When you are done choosing filters, type 'Done'";
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
    }
}
