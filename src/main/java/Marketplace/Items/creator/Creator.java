package Marketplace.Items.creator;

import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;

public interface Creator <T extends Item>{
    Item makeItem(ItemCategories category);
}
