package Marketplace.Items.types;

public class Misc extends Item{

    // Subclass of Item, which represents items under the miscellaneous category

    public Misc(String title, String item_description, String contact_num, String contact_email, String password,
                double price, Item.campus campus) {
        // Constructor with parameters
        super(title, item_description, contact_num, contact_email, password, price, campus);
    }

    public Misc() {
        // Blank constructor
        super();
    }
}
