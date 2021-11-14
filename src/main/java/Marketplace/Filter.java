package Marketplace;

import Marketplace.Items.types.Item;

import java.util.ArrayList;

public interface Filter {
    ArrayList<Item> apply(Iterable<Item> tofilter);
}
