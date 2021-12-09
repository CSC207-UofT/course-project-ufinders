package Marketplace;

import java.util.ArrayList;
import java.util.HashMap;

import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Item;
import Marketplace.comparators.HighPrice;
import Marketplace.comparators.LowPrice;
import Marketplace.filters.*;
import Marketplace.Items.types.*;

public class MarketplaceController {

    /**
     * Passes information about an item to the appropriate ItemManager method for creation.
     * @param type the item type
     * @param info the list of info gathered about the item
     */
    public static void sellingInfo(String type, ArrayList<String> info){
        String name = info.get(0);
        String description = info.get(1);
        double price = Double.parseDouble(info.get(2));
        String phone = info.get(3);
        String email = info.get(4);
        String password = info.get(5);
        Item.campus campus = Item.campus.valueOf(info.get(6));
        switch (type) {
            case "Electronic":
                Item.condition condition = Item.condition.valueOf(info.get(7));
                String specifications = info.get(8);
                new ItemManager().CreatePostElectronic(name, description, price, phone, email,
                        password, campus, condition, specifications);
                break;
            case "Animal":
                String animaltype = info.get(7);
                new ItemManager().CreatePostAnimal(name, description, price, phone, password, email, campus, animaltype);
                break;
            case "Textbook":
                String course = info.get(7);
                new ItemManager().CreatePostTextbook(name, description, price, phone, email, password, campus, course);
                break;
            case "Clothing":
                Item.condition condition2 = Item.condition.valueOf(info.get(7));
                Clothes.size size = Clothes.size.valueOf(info.get(8));
                new ItemManager().CreatePostClothes(name, description, price, phone, password,
                        email, campus, size, condition2);
                break;
            case "Other":
                new ItemManager().CreatePostMisc(name, description, price, phone, email, password, campus);
                break;
        }
    }

    /**
     * Makes a search based on user specifications and returns the results from the search
     * @param choices a hashmap with keys as the type of filter needed and values as the choice for that filter
     * @return the items that the user has searched for
     */
    public static ArrayList<Item> startSearch(HashMap<String, String> choices){
        Searcher search = new Searcher();
        for (String key : choices.keySet()){
            makeFilter(choices, key, search);
        }
        return search.execute();
    }

    /**
     * attempts to remove the given post and returns whether the post was sucessfully removed
     * @param title title of post to be removed
     * @param password password for post to be removed
     * @return true if the post is removed
     */
    public static boolean removePost(String title, String password){
//    Will change the title to ID when we create an ID instead, but should users be able to remember their ID?
        return ItemManager.removePost(title, password);
    }

    /**
     * makes a filter and adds it to the search using the given key and hashmap
     * @param choices a hashmap with keys as the type of filter needed and values as the choice for that filter
     * @param key the hashmap key for a type of filter
     * @param search the search object to add filters to
     */

    private static void makeFilter(HashMap<String, String> choices, String key, Searcher search){
        switch (key) {
            case "sort":
                if (choices.get("sort").equals("pricehighlow")) {
                    search.addSorter(new Sorter(new HighPrice()));
                } else if (choices.get("sort").equals("pricelowhigh")) {
                    search.addSorter(new Sorter(new LowPrice()));
                }
            case "keyword":
                search.addFilter(new wordFilter(choices.get("keyword")));
                break;
            case "type":
                search.addFilter(new typeFilter(ItemCategories.valueOf(choices.get("type"))));
                break;
            case "campus":
                search.addFilter(new campusFilter(Item.campus.valueOf(choices.get("campus"))));
                break;
            case "lowprice":
                search.addFilter(new priceFilter(Double.parseDouble((choices.get("lowprice"))),
                        Double.parseDouble((choices.get("highprice")))));
                break;
            case "condition":
                search.addFilter(new conditionFilter(Item.condition.valueOf(choices.get("condition"))));
                break;
            case "size":
                search.addFilter(new sizeFilter(Clothes.size.valueOf(choices.get("size"))));
                break;
            case "course":
                search.addFilter(new courseFilter(choices.get("course")));
                break;
        }
    }

}