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
        for (Item i: tofilter){
            //make sure it's clothes or electronics - after merge
            if (i.getCondition() = this.condition){
                filtered.add(i);
            }
        }
        return filtered;
    }
}
