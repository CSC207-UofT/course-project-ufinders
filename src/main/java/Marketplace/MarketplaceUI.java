package Marketplace;

import Marketplace.Items.types.ItemCategories;
import User.UFindIntro;

import java.util.*;

public class MarketplaceUI {

    /**
     * Prompts the user to choose a section of the marketplace to enter
     * and directs them there.
     *
     */
    public static void intro() {
        MarketplaceWindow w = new MarketplaceWindow();
        String choice = w.getChoice("Do you want to buy, sell, remove item, or exit the marketplace?", new String[]{"buy", "sell", "remove", "exit"});
        switch (choice) {
            case "buy":
                MakeSearch();
                break;
            case "sell":
                NewPost();
                break;
            case "remove":
                removePostInfo();
                break;
            case "exit":
                new UFindIntro();
        }
    }

    /**
     * Gets information on what item the user is selling and passes that information to MarketplaceController.
     *
     *
     */
    private static void NewPost(){
        ArrayList<String> info = new ArrayList<>();
        MarketplaceWindow w = new MarketplaceWindow();
        info.add(w.getInput("What is the name of the item you're selling?"));
        info.add(w.getInput("What is the description of the item you're selling?"));
        info.add(w.getInput("What is the price of the item you're selling?"));
        info.add(w.getInput("What is your phone number?"));
        info.add(w.getInput("What is your email?"));
        info.add(w.getInput("What password would you like to use to delete this post after the item is sold?"));
        info.add(w.getChoice("What is the campus you're selling the item from?", new String[]{"UTM", "UTSG", "UTSC"}));
        String typeentry = w.getChoice("What is the type of the item you're selling?", new String[]{"Animal", "Clothing", "Electronic", "Textbook", "Other"});
        switch (typeentry) {
            case "Electronic":
                info.add(w.getChoice("What is the condition of your electronic? ", new String[]{"New", "Used", "LikeNew"}));
                info.add(w.getInput("What are the technical specifications of the electronic you are selling?"));
                break;
            case "Animal":
                info.add(w.getInput("What type of animal are you selling?"));
                break;
            case "Textbook":
                info.add(w.getInput("What is the course this textbook is for?"));
                break;
            case "Clothing":
                info.add(w.getChoice("What is the condition of the clothing?", new String[]{"New", "Used", "LikeNew"}));
                info.add(w.getChoice("What is the size of the clothing?", new String[]{"XS", "S", "M", "L", "XL"}));
                break;
        }
        MarketplaceController.sellingInfo(typeentry, info);
        w.displayInfo("Your item has been posted!");
        intro();
    }

    /**
     * Gets information on what item the user is searching for and passes it to MarketplaceController.
     *
     */
    private static void MakeSearch(){
        MarketplaceWindow w = new MarketplaceWindow();
        HashMap<String, String> choices = new HashMap<>();
        String typechoice = "misc";
        if (w.getChoice("Do you want to search for a specific type of item?", new String[]{"Yes", "No"}).equals("Yes")){
            typechoice = w.getChoice("What type of item would you like to search for?",
                    new String[]{"textbook", "clothes", "electronics", "animal", "misc"});
            choices.put("type", typechoice);
        }
        choices.put("keyword", (w.getInput("Please enter a keyword for your search (ex. computer" +
                ", desk, biology):")));
        ArrayList<String> options = getOptions(ItemCategories.valueOf(typechoice));
        String filteranswer = w.getChoice("Do you want to filter by any of these options? " +
                "If so, click one and then choose the value you want the items you see to have", options.toArray(new String[0]));
        while (!filteranswer.equals("Done")){
            switch (filteranswer) {
                case "Campus":
                    choices.put("campus", (w.getChoice("What campus do you want the item to be from?", new String[]{"UTM", "UTSG", "UTSC"})));
                    options.remove("Campus");
                    break;
                case "Price":
                    choices.put("lowprice", w.getInput("What is the lowest price you " +
                            "want to search for?"));
                    choices.put("highprice", w.getInput("What is the highest " +
                            "price you want to search for?"));
                    options.remove("Price");
                    break;
                case "Condition":
                    choices.put("condition", (w.getChoice("What condition do you want your item to be in?", new String[]{"New", "Used", "LikeNew"})));
                    break;
                case "Size":
                    choices.put("size", (w.getChoice("What size do you want your item to be?", new String[]{"XS", "S", "M", "L", "XL"})));
                    break;
                case "Course":
                    choices.put("course", (w.getInput("What course do you want your textbook to be for?")));
                    break;
            }
            filteranswer = w.getChoice("Do you want to filter by any of these options? " +
                    "If so, click one and then choose the value you want the items you see to have. Click Done when all filters you want have been applied", options.toArray(new String[0]));
        }
        String sortchoice = w.getChoice("do you want to sort your results by price?", new String[]{"Yes", "No"});
        if (Objects.equals(sortchoice, "Yes")) {
            String sortkey = w.getChoice("do you want to sort by high to low or low to high?", new String[]{"high to low", "low to high"});
            if (sortkey.equals("high-low")) {
                choices.put("sort", "pricehighlow");
            } else if (sortkey.equals("low-high")) {
                choices.put("sort", "pricelowhigh");
            }
        }
        w.displayInfo("Searching now!");
        w.displayItems(MarketplaceController.startSearch(choices));
    }

    /**
     * Gets information on what filters the searcher wants.
     *
     */
    private static ArrayList<String> getOptions(ItemCategories type){
        ArrayList<String> options = new ArrayList<>();
        options.add("Campus");
        options.add("Price");
        if (type == ItemCategories.electronics){
            options.add("Condition");
        }
        if (type == ItemCategories.clothes){
            options.add("Condition");
            options.add("Size");
        }
        if (type == ItemCategories.textbook){
            options.add("Course");
        }
        options.add("Done");
        return options;
    }

    /**
     * gets information for the user to remove a post and passes it to MarketplaceController
     */
    private static void removePostInfo(){
        MarketplaceWindow w = new MarketplaceWindow();
//    Will change the title to ID when we create an ID instead, but should users be able to remember their ID?
        String title = w.getInput("Please enter the title of the item you are removing");
        String password = w.getInput("Please enter the password for the item.");
        if (MarketplaceController.removePost(title, password)){
            w.displayInfo("You have successfully removed the item!");
            intro();
        }
        else {
            String fail = w.getChoice("You have entered the wrong title or password. Would you like to try again?", new String[]{"Yes", "No"});
            if (Objects.equals(fail, "Yes")){
                removePostInfo();
            }
            else{
                intro();
            }
        }
    }

}
