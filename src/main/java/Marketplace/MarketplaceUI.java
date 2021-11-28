package Marketplace;

import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;

import java.util.*;

public class MarketplaceUI {

    /**
     * Prompts the user to choose a section of the marketplace to enter
     * and directs them there.
     *
     */
    public static void intro() {
        String choice = get_input("Do you want to buy, sell, remove item, or ex" +
                "it the marketplace? (Write 'buy,' 'sell,' 'remove' or 'exit')");
        switch (choice) {
            case "buy":
                MakeSearch();
                break;
            case "sell":
                NewPost();
                break;
            case "remove":
                remove_post();
                break;
            case "exit":
                System.exit(0);
            default:
                intro();
                break;
        }
    }

    /**
     * Gets information on what item the user is selling and passes to Sell_Buy.
     *
     *
     */
    private static void NewPost(){
        ArrayList<Object> info = new ArrayList<>();
        info.add(get_input("What is the name of the item you're selling?"));
        info.add(get_input("What is the description of the item you're selling?"));
        info.add(Double.parseDouble(get_input("What is the price of the item you're selling?")));
        info.add(get_input("What is your phone number?"));
        info.add(get_input("What is your email?"));
        info.add(get_input("What password would you like to use to delete this post after the item is sold?"));
        info.add(Item.campus.valueOf(get_input("What is the campus you're selling the item from? Your options are UTM, UTSG, and UTSC")));
        String typeentry = get_input("What is the type of the item you're selling? Your options are 'Animal' 'Clothing' 'Electronic' 'Textbook' or 'Other'");
        switch (typeentry) {
            case "Electronic":
                info.add(Item.condition.valueOf(get_input("What is the condition of your electronic? " +
                        "Your options are: New, Used, LikeNew")));
                info.add(get_input("What are the technical specifications of the electronic you are selling?"));
                break;
            case "Animal":
                info.add(get_input("What type of animal are you selling?"));
                break;
            case "Textbook":
                info.add(get_input("What is the condition of the textbook?"));
                break;
            case "Clothing":
                info.add(Item.condition.valueOf(get_input("What is the condition of the clothing? " +
                        "Your options are: New, Used, LikeNew")));
                info.add(Clothes.size.valueOf(get_input("What is the size of the clothing? " +
                        "Your options are: XS, S, M, L, XL")));
                break;
        }
        MarketplaceController.selling_info(typeentry, info);
        System.out.println("Your post has been created!");
        intro();

    }

    /**
     * Gets information on what item the user is searching for.
     *
     */
    private static void MakeSearch(){
        ItemCategories type = ItemCategories.misc;
        HashMap<String, Object> choices = new HashMap<>();
        if (get_input("Do you want to search for a specific type of item? (Y/N)").equals("Y")){
            String typechoice = get_input("If you would like to search only textbooks, type 'textbook'\nIf you " +
                    "would like to search only clothing, type 'clothes'\nIf you would like to search only electronics, " +
                    "type 'electronics'\nIf you would like to search only animals, type 'animal'\nIf you changed your mind, " +
                    "type 'done'");
            if (!typechoice.equals("done")) {
                choices.put("type", ItemCategories.valueOf(typechoice));
            }
        }
        choices.put("keyword", (get_input("Please enter a keyword for your search (ex. computer" +
                ", desk, biology):")));
        String filteranswer = get_input(get_prompt(type));
        while (!filteranswer.equals("Done")){
            switch (filteranswer) {
                case "Campus":
                    choices.put("campus", ((get_input("What campus do you want your item to be from? Options are: UTSG, UTSC, UTM"))));
                    break;
                case "Price":
                    choices.put("price", new String[]{get_input("What is the lowest price you " +
                            "want to search for?"), get_input("What is the highest " +
                            "price you want to search for?")});
                    break;
                case "Condition":
                    choices.put("condition", get_input("What condition do you want your item to be in? Options are: New, Used, LikeNew"));
                    break;
                case "Size":
                    choices.put("size", get_input("What size do you want your item to be? Options are: XS, S, M, L, XL"));
                    break;
                case "Course":
                    choices.put("course", get_input("What course do you want your textbook to be for?"));
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
                choices.put("sort", "pricehighlow");
            } else if (sortkey.equals("low-high")) {
                choices.put("sort", "pricelowhigh");
            }
        }
        System.out.println("Loading your search");
        MarketplaceController.startSearch(choices);
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
        if (MarketplaceController.remove_post(title, password)){
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
