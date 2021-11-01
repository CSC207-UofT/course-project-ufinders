package Marketplace;

import java.util.ArrayList;

public interface ISorter {
    void setDirection(String direction);
    void setType(String type);
    Iterable<Item> sort(ArrayList<Item> tosort);
}
