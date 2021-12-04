package Marketplace.Items.types;

public class Electronic extends Item {

    // Subclass of Item, which represents items under the electronic category

    /**
     * Extra attributes which outline the condition of the item (using condition enum), and a String for any technical
     * specifications
     */
    private Item.condition condition;
    private String techSpecifications;

    public Electronic(){
        // Blank Constructor
        super();
        this.condition = null;
        this.techSpecifications = null;
    }

    public Electronic(String title, String itemDescription, String contactNum, String contactEmail, String password,
                      double price, Item.campus campus, Item.condition condition, String techSpecifications) {
        // Constructor with parameters
        super(title, itemDescription, contactNum, contactEmail, password, price, campus);
        this.condition = condition;
        this.techSpecifications = techSpecifications;
    }

    public void edit(String title, String itemDescription, String contactNum, String contactEmail,
                     String password, double price, Item.campus campus, condition condition,
                     String tech_specifications) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, itemDescription, contactNum, contactEmail, password, price, campus);
        setCondition(condition);
        setTechSpecifications(tech_specifications);

    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - " + this.getCondition() + ", Price: " + this.getPrice() + ", Description: " + this.getItemDescription() +
                ", Tech Specifications: " + this.getTechSpecifications() + ", Contact info: " + this.getContactEmail()
                + " / " + this.getContactNum() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public Item.condition getCondition() {
        return condition;
    }

    public void setCondition(Item.condition condition) {
        this.condition = condition;
    }

    public String getTechSpecifications() {
        return techSpecifications;
    }

    public void setTechSpecifications(String techSpecifications) {
        this.techSpecifications = techSpecifications;
    }
}
