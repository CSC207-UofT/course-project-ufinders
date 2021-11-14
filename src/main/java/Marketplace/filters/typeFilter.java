package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Items.types.*;

import java.util.ArrayList;

public class typeFilter implements Filter {

    private final ItemCategories type;

    /**
     * Make a new filter that selects items of the given type
     * @param type  the type that items should be
     */
    public typeFilter(ItemCategories type){
        this.type = type;
    }

    /**
     * Filters the given list so that only items of this filter's type are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            if (this.checktype(i)){
                filtered.add(i);
            }
        }
        return filtered;
    }

    /**
     * see if the item matches the filter's type
     * @param item the item to be checked
     * @return true if the item matches the type, false if not
     */
    private boolean checktype(Item item){
        if (this.type == ItemCategories.textbook){
            return item instanceof Textbook;
        }
        else if (this.type == ItemCategories.clothes){
            return item instanceof Clothes;
        }
        else if (this.type == ItemCategories.electronics){
            return item instanceof Electronic;
        }
        else if (this.type == ItemCategories.misc){
            return item instanceof Misc;
        }
        else if (this.type == ItemCategories.animal){
            return item instanceof Animal;
        }
        return false;
    }
}