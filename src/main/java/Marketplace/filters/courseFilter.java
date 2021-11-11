package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class courseFilter implements Filter {

    private final String course;

    /**
     * Make a new filter that selects textbooks for the given course
     * @param course  the course that textbooks should be for
     */
    public courseFilter(String course){
        this.course = course;
    }

    /**
     * Filters the given list so that only textbooks with this filter's course are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            //make sure it's textbook - after merge
            if (i.getCourse() = this.course){
                filtered.add(i);
            }
        }
        return filtered;
    }
}