package Marketplace;

import java.util.ArrayList;
import java.util.List;

public interface Filter {
    public ArrayList<Item> apply(Iterable<Item> tofilter);
}
