package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class conditionFilter implements Filter {

    private final String condition;

    public conditionFilter(String condition){
        this.condition = condition;
    }

    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        //make it specifically electronic
        for (Item i: tofilter){
            if (i.condition() = this.condition){
                filtered.add(i);
            }
        }
        return filtered;
    }
}
