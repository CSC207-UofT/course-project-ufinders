package Marketplace.filters;

import Marketplace.Filter;
import Marketplace.Item;

import java.util.ArrayList;

public class priceFilter implements Filter {
    private final double LowPrice;

    private final double HighPrice;

    public priceFilter(double lowprice, double highprice){
        this.HighPrice = highprice;
        this.LowPrice = lowprice;
    }

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
