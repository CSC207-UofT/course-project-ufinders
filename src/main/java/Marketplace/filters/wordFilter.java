package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Items.types.Item;

import java.util.ArrayList;

public class wordFilter implements Filter {

    private final String keyword;

    /**
     * Make a new filter that selects items containing the given keyword
     * @param keyword  the keyword the items should mention
     */
    public wordFilter(String keyword){
        this.keyword = keyword;
    }

    /**
     * Filters the given list so that only items that mention this filter's keyword are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            if (i.getName().contains(keyword)||i.getItem_description().contains(keyword)){
                filtered.add(i);
            }
        }
        return filtered;
    }
}