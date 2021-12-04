package Marketplace.Items.types;

public class Electronic extends Item {

    // Subclass of Item, which represents items under the electronic category

    /**
     * Extra attributes which outline the condition of the item (using condition enum), and a String for any technical
     * specifications
     */
    private Item.condition condition;
    private String tech_specifications;

    public Electronic(){
        // Blank Constructor
        super();
        this.condition = null;
        this.tech_specifications = null;
    }

    public Electronic(String title, String item_description, String contact_num, String contact_email, String password,
                      double price, Item.campus campus, Item.condition condition, String tech_specifications) {
        // Constructor with parameters
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.condition = condition;
        this.tech_specifications = tech_specifications;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email,
                     String password, double price, Item.campus campus, condition condition,
                     String tech_specifications) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setCondition(condition);
        setTech_specifications(tech_specifications);

    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - " + this.getCondition() + ", Price: " + this.getPrice() + ", Description: " + this.getItem_description() +
                System.lineSeparator() + ", Tech Specifications: " + this.getTech_specifications() + ", Contact info: " + this.getContact_email()
                + " / " + this.getContact_num() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public Item.condition getCondition() {
        return condition;
    }

    public void setCondition(Item.condition condition) {
        this.condition = condition;
    }

    public String getTech_specifications() {
        return tech_specifications;
    }

    public void setTech_specifications(String tech_specifications) {
        this.tech_specifications = tech_specifications;
    }
}
