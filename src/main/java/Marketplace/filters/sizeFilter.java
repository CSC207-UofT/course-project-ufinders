package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Item;

import java.util.ArrayList;

public class sizeFilter implements Filter {

    private final Clothes.size size;

    /**
     * Make a new filter that selects specific clothes from the given campus
     * @param size  the size that clothing items should be
     */
    public sizeFilter(Clothes.size size){
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
            if (i instanceof Clothes && ((Clothes) i).getSize() == this.size){
                filtered.add(i);
            }
        }
        return filtered;
    }
}