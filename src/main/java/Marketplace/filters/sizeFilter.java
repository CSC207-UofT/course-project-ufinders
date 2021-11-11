package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class sizeFilter implements Filter {

    private final String size;

    public sizeFilter(String size){
        this.size = size;
    }

    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            //make sure it's clothes - after merge
            if (i.getSize() = this.size){
                filtered.add(i);
            }
        }
        return filtered;
    }
}