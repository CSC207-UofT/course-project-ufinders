package Marketplace.Items.types;

public class Animal extends Item{

    // Subclass of Item, which represents items under the animal category

    // String representation of the type of animal, such as 'bird', 'cat', 'dog'...
    private String animal_type;

    public Animal(){
        // Blank Constructor
        super();
        this.animal_type = null;
    }

    public Animal(String title, String item_description, String contact_num, String contact_email, String password,
                   double price, Item.campus campus, String animal_type) {
        // Constructor with parameters
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.animal_type = animal_type;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, Item.campus campus, String animal_type) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setAnimal_type(animal_type);
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - " + this.getAnimal_type() + ", Price: " + this.getPrice() + ", Description: " + this.getItem_description() + System.lineSeparator() +
                ", Contact info: " + this.getContact_email() + " / " + this.getContact_num() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }
}
