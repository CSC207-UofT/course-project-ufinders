package Marketplace.Items.types;

import java.io.Serializable;

public abstract class Item implements Serializable {

    /**
     * An abstract item class that stores the users' information and item's attributes
     */

    private String name;
    private String itemDescription;
    private String contactNum;
    private String contactEmail;
    private String password;
    private double price;
    private int id;

    private static int idCounter = 0;

    public enum campus{
        UTSG, UTM, UTSC
    }
    private campus campus;
    public enum condition{
        Used, New, LikeNew
    }
    private condition condition;

    public Item(String title, String itemDescription, String contactNum, String contactEmail, String password,
                double price, campus campus){
        // Constructor with all parameters for an Item
        this.name = title;
        this.itemDescription = itemDescription;
        this.contactNum = contactNum;
        this.contactEmail = contactEmail;
        this.password = password;
        this.price = price;
        this.campus = campus;
        this.id = idCounter;
        idCounter += 1;
    }

    public Item(){
        // Constructor for an empty item
        this.name = null;
        this.itemDescription = null;
        this.contactNum = null;
        this.contactEmail = null;
        this.password = null;
        this.price = 0.0;
        this.campus = null;
        this.id = idCounter;
        idCounter += 1;
    }

    public void edit(String title, String itemDescription, String contactNum, String contactEmail, String password,
                     double price, campus campus){
        // Method which sets all attributes to the selected parameters
        setName(title);
        setItemDescription(itemDescription);
        setContactNum(contactNum);
        setContactEmail(contactEmail);
        setPassword(password);
        setPrice(price);
        setCampus(campus);
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + ", Price: " + this.getPrice() + ", Description: " + this.getItemDescription() + System.lineSeparator() +
                ", Contact info: " + this.getContactEmail() + " / " + this.getContactNum() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getContactEmail() {
        return contactEmail;
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

    public void setItemDescription(String itemDescription) {this.itemDescription = itemDescription;}

    public void setContactNum(String contactNum) {this.contactNum = contactNum;}

    public void setContactEmail(String contactEmail) {this.contactEmail = contactEmail;}

    public void setPassword(String password) {this.password = password;}

    public void setPrice(double price) {this.price = price;}

    public void setCampus(Item.campus campus) {this.campus = campus;}

    public void setCondition(Item.condition condition) {this.condition = condition;}
}
