package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

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
        if (this.type){
            //figure this out after merge
        }
    }
}