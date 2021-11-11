package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class priceFilter implements Filter {
    private final double LowPrice;

    private final double HighPrice;


    /**
     * Make a new filter that selects item within the given prices
     * @param lowprice  the lowest price an item should be
     * @param highprice the highest price an item should be
     */
    public priceFilter(double lowprice, double highprice){
        this.HighPrice = highprice;
        this.LowPrice = lowprice;
    }

    /**
     * Filters the given list so that only items between this filter's LowPrice and HighPrice are returned
     * @param tofilter the list to be filtered
     * @return the list returned
     */
    @Override
    public ArrayList<Item> apply(Iterable<Item> tofilter){
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: tofilter){
            if (i.getPrice() > this.LowPrice && i.getPrice() < this.HighPrice){
                filtered.add(i);
            }
        }
        return filtered;
    }
}
