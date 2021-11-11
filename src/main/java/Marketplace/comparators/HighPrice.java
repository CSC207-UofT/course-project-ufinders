package Marketplace.comparators;

import Marketplace.Item;

import java.util.Comparator;

public class HighPrice implements Comparator<Item> {

    /**
     * compares two Items to determine which has a higher price
     * @param i1  the first Item to be compared
     * @param i2  the second Item to be compared
     * @return  1 only if i1's price is higher, -1 only if i1's price is lower, and 0 if the prices are the same
     */
    @Override
    public int compare(Item i1, Item i2) {
        if (i1.getPrice() > i2.getPrice()){
            return 1;
        }
        else if (i1.getPrice() < i2.getPrice()){
            return -1;
        }
        return 0;
    }
}
