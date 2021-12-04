package Marketplace.Items.types;

public class Misc extends Item{

    // Subclass of Item, which represents items under the miscellaneous category

    public Misc(String title, String itemDescription, String contactNum, String contactEmail, String password,
                double price, Item.campus campus) {
        // Constructor with parameters
        super(title, itemDescription, contactNum, contactEmail, password, price, campus);
    }

    public Misc() {
        // Blank constructor
        super();
    }
}
