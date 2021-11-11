package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class sizeFilter implements Filter {

    private final String size;

    /**
     * Make a new filter that selects specific clothes from the given campus
     * @param size  the size that clothing items should be
     */
    public sizeFilter(String size){
        this.size = size;
    }

    /**
     * Filters the given list so that only clothes with this filter's size are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            //make sure it's clothes - after merge
            if (i.getSize() = this.size){
                filtered.add(i);
            }
        }
        return filtered;
    }
}