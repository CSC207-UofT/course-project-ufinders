package Marketplace.Items.types;

import java.io.Serializable;

public abstract class Item implements Serializable {

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
                double price, campus campus){
        // Constructor with all parameters for an Item
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

    public Item(){
        // Constructor for an empty item
        this.name = null;
        this.item_description = null;
        this.contact_num = null;
        this.contact_email = null;
        this.password = null;
        this.price = 0.0;
        this.campus = null;
        this.id = id_counter;
        id_counter += 1;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, campus campus){
        // Method which sets all attributes to the selected parameters
        setName(title);
        setItem_description(item_description);
        setContact_num(contact_num);
        setContact_email(contact_email);
        setPassword(password);
        setPrice(price);
        setCampus(campus);
    }

    // Getters and setters

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

    public int getId() {return id;}

    public Item.campus getCampus() {return campus;}

    public Item.condition getCondition() {return condition;}

    public void setName(String name) {this.name = name;}

    public void setItem_description(String item_description) {this.item_description = item_description;}

    public void setContact_num(String contact_num) {this.contact_num = contact_num;}

    public void setContact_email(String contact_email) {this.contact_email = contact_email;}

    public void setPassword(String password) {this.password = password;}

    public void setPrice(double price) {this.price = price;}

    public void setCampus(Item.campus campus) {this.campus = campus;}

    public void setCondition(Item.condition condition) {this.condition = condition;}
}
