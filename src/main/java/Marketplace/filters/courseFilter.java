package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class courseFilter implements Filter {

    private final String course;

    public courseFilter(String course){
        this.course = course;
    }

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