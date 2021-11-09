package Marketplace.Items.types;

public class Misc extends Item{

    public Misc(String title, String item_description, String contact_num, String contact_email, String password,
                double price, Item.campus campus) {
        super(title, item_description, contact_num, contact_email, password, price, campus);
    }

    public Misc() {
        super();
    }
}
