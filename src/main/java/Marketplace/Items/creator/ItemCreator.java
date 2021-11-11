package Marketplace.Items.creator;

import Marketplace.Items.types.*;

public class ItemCreator {

    // Factory class, which creates instances of item subclasses - is a Virtual Constructor

    public Item makeItem(ItemCategories category) {
        // Method will return an instance of a subclass of Item, cast to Item based on the enum parameter inputted
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
