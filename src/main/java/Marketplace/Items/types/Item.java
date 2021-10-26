package Marketplace.Items.types;

public abstract class Item {

    /**
     * An abstract item class that stores the users' information and item's attributes
     *
     * @param name the item's name
     * @param item_description the description of the item
     * @param contact_num the seller's phone number
     * @param contact_email the seller's email
     * @param price the item's price
     * @param password the item's deletion password
     * @param campus the seller's campus
     * @param condition the item's condition
     * @param picture the item's picture
     */

    private String name;
    private String item_description;
    private String contact_num;
    private String contact_email;
    private String password;
    private double price;
    private int id;

    private static int id_counter = 0;

    /* TODO: add picture attribute
     */

    public enum campus{
        UTSG, UTM, UTSC
    }
    private campus campus;
    public enum condition{
        Used, New, LikeNew
    }
    private condition condition;


    public Item(String title, String item_description, String contact_num, String contact_email, String password,
                double price, Item.campus campus){
        this.name = title;
        this.item_description = item_description;
        this.contact_num = contact_num;
        this.contact_email = contact_email;
        this.password = password;
        this.price = price;
        this.campus = campus;
        this.id = id_counter;
        id_counter += 1;
    }

    public String getName() {
        return name;
    }

    public String getItem_description() {
        return item_description;
    }

    public String getContact_num() {
        return contact_num;
    }

    public String getContact_email() {
        return contact_email;
    }

    public String getPassword() {
        return password;
    }

    public double getPrice() {
        return price;
    }
}
