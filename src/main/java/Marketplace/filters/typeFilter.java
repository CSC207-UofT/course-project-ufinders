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
            if (this.checktype(i)){
                filtered.add(i);
            }
        }
        return filtered;
    }

    private boolean checktype(Item item){
        if (this.type){
            //figure this out after merge
        }
    }
}