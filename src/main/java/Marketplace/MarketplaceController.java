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
     * Gets information on what item the user is selling and passes to Sell_Buy.
     *
     *
     */
    public static void selling_info(String type, ArrayList<String> info){
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
                new ItemManager().CreatePostAnimal(name, description, price, phone, email, password, campus, animaltype);
                break;
            case "Textbook":
                String course = info.get(7);
                new ItemManager().CreatePostTextbook(name, description, price, phone, email, password, campus, course);
                break;
            case "Clothing":
                Item.condition condition2 = Item.condition.valueOf(info.get(7));
                Clothes.size size = Clothes.size.valueOf(info.get(8));
                new ItemManager().CreatePostClothes(name, description, price, phone, email,
                        password, campus, size, condition2);
                break;
            case "Other":
                new ItemManager().CreatePostMisc(name, description, price, phone, email, password, campus);
                break;
        }
    }

    /**
     * Gets information on what item the user is searching for and make the search
     *
     * @return the items that the user has searched for
     */
    public static ArrayList<Item> startSearch(HashMap<String, String> choices){
        Searcher search = new Searcher();
        search.addFilter(new typeFilter(ItemCategories.valueOf(choices.get("type"))));
        search.addFilter(new wordFilter(choices.get("keyword")));
        search.addFilter(new campusFilter(Item.campus.valueOf(choices.get("campus"))));
        if (choices.get("price") != null){
            search.addFilter(new priceFilter(Double.parseDouble((choices.get("lowprice"))),
                    Double.parseDouble((choices.get("highprice")))));
        }
        search.addFilter(new conditionFilter(Item.condition.valueOf(choices.get("condition"))));
        search.addFilter(new sizeFilter(Clothes.size.valueOf(choices.get("size"))));
        search.addFilter(new courseFilter(choices.get("course")));
        if (choices.get("sort").equals("pricehighlow")) {
            search.addSorter(new Sorter(new LowPrice()));
        } else if (choices.get("sort").equals("pricelowhigh")) {
            search.addSorter(new Sorter(new HighPrice()));
        }
        return search.execute();
    }

    public static boolean remove_post(String title, String password){
//    Will change the title to ID when we create an ID instead, but should users be able to remember their ID?
        if (ItemManager.removePost(title, password)){
            System.out.println("You have successfully removed item!");
            return true;
        }
        return false;
    }

}
