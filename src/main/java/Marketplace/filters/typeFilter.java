package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class typeFilter implements Filter {

    private final ItemCategories type;

    public typeFilter(ItemCategories type){
        this.type = type;
    }

    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            //if i is TYPE <-- based on the type itemcategory
            if (i.getCampus() = this.campus){
                filtered.add(i);
            }
        }
        return filtered;
    }
}