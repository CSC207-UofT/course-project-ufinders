package Marketplace.Items.creator;

import Marketplace.Items.types.*;

public class ItemCreator {
    public Item makeItem(ItemCategories category) {
        if(category == ItemCategories.electronics){
            return new Electronic();
        }
        else if (category == ItemCategories.textbook){
            return new Textbook();
        }
        else if(category == ItemCategories.clothes){
            return new Clothes();
        }
        else if(category == ItemCategories.misc){
            return new Misc();
        }
        else if(category == ItemCategories.animal){
            return new Animal();
        }
        else{
            return null;
        }
    }
}