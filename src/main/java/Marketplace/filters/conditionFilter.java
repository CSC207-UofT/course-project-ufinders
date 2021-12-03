package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Items.types.Item;

import java.util.ArrayList;

public class conditionFilter implements Filter {

    private final Item.condition condition;

    /**
     * Make a new filter that selects items in the given condition
     * @param condition  the condition that items should be in
     */
    public conditionFilter(Item.condition condition){
        this.condition = condition;
    }

    /**
     * Filters the given list so that only clothes or electronics with this filter's condition are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            //make sure it's clothes or electronics - after merge
            if (i.getCondition() == this.condition){
                filtered.add(i);
            }
        }
        return filtered;
    }
}
