package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class campusFilter implements Filter {

    private final String campus;

    public campusFilter(String campus){
        this.campus = campus;
    }

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
