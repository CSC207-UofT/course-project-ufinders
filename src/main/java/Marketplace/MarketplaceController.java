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
     * Prompts the user to choose a section of the marketplace to enter
     * and directs them there.
     *
     */

    /**
     * Gets information on what item the user is selling and passes to Sell_Buy.
     *
     *
     */
    public static void selling_info(String type, ArrayList<Object> info){
        //if we slightly change how ItemManager works, I could just pass the type and the info to the item
        // manager so that we avoid repeating all these createpost methods
        switch (type) {
            case "Electronic":
                new ItemManager().CreatePostElectronic(info);
                break;
            case "Animal":
                new ItemManager().CreatePostAnimal(info);
                break;
            case "Textbook":
                new ItemManager().CreatePostTextbook(info);
                break;
            case "Clothing":
                new ItemManager().CreatePostClothes(info);
                break;
            case "Other":
                new ItemManager().CreatePostMisc(info);
                break;
        }
    }

    /**
     * Gets information on what item the user is searching for and make the search
     *
     * @return the items that the user has searched for
     */
    public static ArrayList<Item> startSearch(HashMap<String, Object> choices){
        Searcher search = new Searcher();
        search.addFilter(new typeFilter(ItemCategories.valueOf((String) choices.get("type"))));
        search.addFilter(new wordFilter((String) choices.get("keyword")));
        search.addFilter(new campusFilter(Item.campus.valueOf((String) choices.get("campus"))));
        if (choices.get("price") != null){
            search.addFilter(new priceFilter(Double.parseDouble(((ArrayList<String>) choices.get("price")).get(0)),
                    Double.parseDouble(((ArrayList<String>) choices.get("price")).get(1))));
        }
        search.addFilter(new conditionFilter(Item.condition.valueOf((String) choices.get("condition"))));
        search.addFilter(new sizeFilter(Clothes.size.valueOf((String) choices.get("size"))));
        search.addFilter(new courseFilter((String) choices.get("course")));
        if (choices.get("sort").equals("pricehighlow")) {
            search.addSorter(new Sorter(new LowPrice()));
        } else if (choices.get("sort").equals("pricelowhigh")) {
            search.addSorter(new Sorter(new HighPrice()));
        }
        return search.execute();
    }

    public static boolean remove_post(String title, String password){
//    Will change the title to ID when we create an ID instead, but should users be able to remember their ID?
        if (ItemManager.remove_post(title, password)){
            System.out.println("You have successfully removed item!");
            return true;
        }
        return false;
    }

}
