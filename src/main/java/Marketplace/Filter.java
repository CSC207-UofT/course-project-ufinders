package Marketplace;

import java.util.ArrayList;

public interface Filter {
    public ArrayList<Item> apply(Iterable<Item> tofilter);
}
