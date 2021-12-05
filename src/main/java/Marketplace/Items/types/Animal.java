package Marketplace.Items.types;

public class Animal extends Item{

    // Subclass of Item, which represents items under the animal category

    // String representation of the type of animal, such as 'bird', 'cat', 'dog'...
    private String animalType;

    public Animal(){
        // Blank Constructor
        super();
        this.animalType = null;
    }

    public Animal(String title, String itemDescription, String contactNum, String contactEmail, String password,
                   double price, Item.campus campus, String animalType) {
        // Constructor with parameters
        super(title, itemDescription, contactNum, contactEmail, password, price, campus);
        this.animalType = animalType;
    }

    public void edit(String title, String itemDescription, String contactNum, String contactEmail, String password,
                     double price, Item.campus campus, String animalType) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, itemDescription, contactNum, contactEmail, password, price, campus);
        setAnimalType(animalType);
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - " + this.getAnimalType() + ", Price: " + this.getPrice() + ", Description: " + this.getItemDescription() + System.lineSeparator() +
                ", Contact info: " + this.getContactEmail() + " \n " + this.getContactNum() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
