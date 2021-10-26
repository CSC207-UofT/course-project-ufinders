package Marketplace.Items.creator;

import Marketplace.Items.types.Item;
import Marketplace.Items.types.ItemCategories;
import Marketplace.Items.types.Electronic;

public class ElectronicCreator implements Creator{

    @Override
    public Item makeItem(ItemCategories category) {
        if(category == ItemCategories.electronics){
            return new Electronic();
        }
        else{
            return null;
        }
    }
}
