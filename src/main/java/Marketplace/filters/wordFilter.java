package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class wordFilter implements Filter {

    private final String keyword;

    public wordFilter(String keyword){
        this.keyword = keyword;
    }

    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter) {
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            if (i.getName().contains(keyword)||i.getItem_description().contains(keyword)){
                filtered.add(i);
            }
        }
        return filtered;
    }
}