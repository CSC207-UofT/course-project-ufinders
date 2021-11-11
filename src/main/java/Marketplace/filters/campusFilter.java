package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class campusFilter implements Filter {

    private final String campus;

    /**
     * Make a new filter that selects items from the given campus
     * @param campus  the campus that items should be from
     */
    public campusFilter(String campus){
        this.campus = campus;
    }

    /**
     * Filters the given list so that only items with this filter's campus are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            if (i.getCampus() = this.campus){
                filtered.add(i);
            }
        }
        return filtered;
    }
}
