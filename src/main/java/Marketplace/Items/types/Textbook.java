package Marketplace.Items.types;

public class Textbook extends Item{

    // Subclass of Item, which represents items under the textbook category

    // String representation of a given course
    private String course;

    public Textbook(){
        // Blank constructor.
        super();
        this.course = null;
    }

    public Textbook(String title, String item_description, String contact_num, String contact_email, String password,
                      double price, Item.campus campus, String course) {
        // Constructor with parameters
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.course = course;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, Item.campus campus, String course) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setCourse(course);
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - for " + this.getCourse() + ", Price: " + this.getPrice() + ", Description: " + this.getItem_description() + ", Contact info: " + this.getContact_email()
                + " / " + this.getContact_num() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
