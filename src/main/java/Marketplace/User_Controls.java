package Marketplace;

import java.util.Objects;
import java.util.Scanner;

import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Item;
import Marketplace.comparators.HighPrice;
import Marketplace.comparators.LowPrice;
import Marketplace.filters.*;
import Marketplace.Items.types.*;

public class User_Controls {

    public static void main(String[] args) {
        intro();
    }

    /**
     * Prompts the user to choose a section of the marketplace to enter
     * and directs them there.
     *
     */
    public static void intro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to buy, sell, remove item, or exit the marketplace? (Write 'buy,' 'sell,' 'remove' or 'exit')");
        String segmentchoice = input.nextLine();
        if (Objects.equals(segmentchoice, "buy")){
            buying_info();
        }
        else if (Objects.equals(segmentchoice, "sell")){
            selling_info();
        }
        else if (Objects.equals(segmentchoice, "remove")){
            remove_post();
        }
        else if (Objects.equals(segmentchoice, "exit")){
            String[] arguments = new String[]{"hi"};
            User_UI.main(arguments);
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
        String name = get_input("What is the name of the item you're selling?");
        String description = get_input("What is the description of the item you're selling?");
        String price = get_input("What is the price of the item you're selling?");
        String contact = get_input("What is your phone number?");
        String email = get_input("What is your email?");
        String password = get_input("What password would you like to use to delete this post after the item is sold?");
        double price1 = Double.parseDouble(price);
        Item.campus campus = Item.campus.valueOf(get_input("What is the campus you're selling the item from? Your options are UTM, UTSG, and UTSC"));
        String typeentry = get_input("What is the type of the item you're selling? Your options are 'Animal' 'Clothing' 'Electronic' 'Textbook' or 'Other'");
        switch (typeentry) {
            case "Electronic":
                Item.condition Econdition = Item.condition.valueOf(get_input("What is the condition of your electronic? " +
                        "Your options are: New, Used, LikeNew"));
                String tech_specifications = get_input("What are the technical specifications of the electronic you are selling?");
                new ItemManager().CreatePostElectronic(name, description, price1, contact, email, password, campus, Econdition, tech_specifications);
                break;
            case "Animal":
                String animal_type = get_input("What type of animal are you selling?");
                new ItemManager().CreatePostAnimal(name, description, price1, contact, password, email, campus, animal_type);
                break;
            case "Textbook":
                String course = get_input("What is the condition of the textbook?");
                new ItemManager().CreatePostTextbook(name, description, price1, contact, password, email, campus, course);
                break;
            case "Clothing":
                Item.condition Ccondition = Item.condition.valueOf(get_input("What is the condition of the clothing? " +
                        "Your options are: New, Used, LikeNew"));
                Clothes.size size = Clothes.size.valueOf(get_input("What is the size of the clothing? " +
                        "Your options are: XS, S, M, L, XL"));
                new ItemManager().CreatePostClothes(name, description, price1, contact, password, email, campus, size, Ccondition);
                break;
            case "Other":
                new ItemManager().CreatePostMisc(name, description, price1, contact, password, email, campus);
                break;
        }
        System.out.println("Your post has been created!");
        intro();

    }

    /**
     * Gets information on what item the user is searching for.
     *
     */
    private static void buying_info(){
        Searcher search = new Searcher();
        ItemCategories type = ItemCategories.misc;
        if (get_input("Do you want to search for a specific type of item? (Y/N)").equals("Y")){
            String typechoice = get_input("If you would like to search only textbooks, type 'textbook'\nIf you " +
                    "would like to search only clothing, type 'clothes'\nIf you would like to search only electronics, " +
                    "type 'electronics'\nIf you would like to search only animals, type 'animal'\nIf you changed your mind, " +
                    "type 'done'");
            if (!typechoice.equals("done")) {
                type = ItemCategories.valueOf(typechoice);
                search.addFilter(new typeFilter(type));
            }
        }
        search.addFilter(new wordFilter((get_input("Please enter a keyword for your search (ex. computer" +
                ", desk, biology):"))));
        String filteranswer = get_input(get_prompt(type));
        while (!filteranswer.equals("Done")){
            switch (filteranswer) {
                case "Campus":
                    search.addFilter(new campusFilter(Item.campus.valueOf(get_input("What campus do you want your item to be from? Options are: UTSG, UTSC, UTM"))));
                    break;
                case "Price":
                    search.addFilter(new priceFilter(Double.parseDouble(get_input("What is the lowest price you " +
                            "want to search for?")), Double.parseDouble(get_input("What is the highest " +
                            "price you want to search for?"))));
                    break;
                case "Condition":
                    search.addFilter(new conditionFilter(Item.condition.valueOf(get_input("What condition do you want your item to be in? Options are: New, Used, LikeNew"))));
                    break;
                case "Size":
                    search.addFilter(new sizeFilter(Clothes.size.valueOf(get_input("What size do you want your item to be? Options are: XS, S, M, L, XL"))));
                    break;
                case "Course":
                    search.addFilter(new courseFilter(get_input("What course do you want your textbook to be for?")));
                    break;
            }
            filteranswer = get_input(get_prompt(type));
        }
        String sortchoice = get_input("do you want to sort your results by price? (Y/N)");
        while (!sortchoice.equals("N")&&!sortchoice.equals("Y")){
                sortchoice = get_input("do you want to sort by price? (Y/N)");
        }
        if (Objects.equals(sortchoice, "Y")) {
            String sortkey = get_input("do you want to sort by high to low or low to high? (high-low/low-high)");
            if (sortkey.equals("high-low")) {
                search.addSorter(new Sorter(new LowPrice()));
            } else if (sortkey.equals("low-high")) {
                search.addSorter(new Sorter(new HighPrice()));
            }
        }
        System.out.println("Loading your search");
        Results.present(search.execute());
    }

    /**
     * Gets information on what filters the searcher wants.
     *
     */
    private static String get_prompt(ItemCategories type){
        String prompt = "Here are your filter options:\nTo filter results by campus, type 'Campus'\nTo filter results by price, type 'Price'";
        if (type == ItemCategories.electronics){
            prompt+= "To filter results by condition, type 'condition'";
        }
        if (type == ItemCategories.clothes){
            prompt+= "To filter results by condition, type 'condition'\nTo filter results by size, type 'size'";
        }
        if (type == ItemCategories.textbook){
            prompt+= "To filter results by course, type 'Course'";
        }
        prompt+= "\nYou can add multiple filters, but please only select one option for each category (ex. only choose one campus, don't go back to choose a second one)\n" +
                "When you are done choosing filters, type 'Done'";
        return prompt;
    }

    private static void remove_post(){
//    Will change the title to ID when we create an ID instead, but should users be able to remember their ID?
        String title = get_input("Please enter the title of the item you are removing");
        String password = get_input("Please enter the password for the item.");
        if (ItemManager.remove_post(title, password)){
            System.out.println("You have successfully removed item!");
            intro();
        }
        else {
            String fail = get_input("You have entered the wrong title or password. Would you like to try again? (Y/N)");
            if (Objects.equals(fail, "Y")){
                remove_post();
            }
            else{
                intro();
//                or should we just go straight to MainController.menu()?
            }
        }
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
