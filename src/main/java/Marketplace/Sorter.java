package Marketplace;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter implements ISorter{

    private final Comparator<Item> comparator;

    /**
     * Creates a new sorter with the given comparator
     * @param comparator the comparator to be used by sorter
     */
    public Sorter(Comparator<Item> comparator){
        this.comparator = comparator;
    }

    @Override
    public Iterable<Item> sort(ArrayList<Item> tosort) {
        tosort.sort(this.comparator);
        return tosort;
    }
}
