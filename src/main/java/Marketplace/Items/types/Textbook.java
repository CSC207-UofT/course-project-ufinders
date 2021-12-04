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

    public Textbook(String title, String itemDescription, String contactNum, String contactEmail, String password,
                      double price, Item.campus campus, String course) {
        // Constructor with parameters
        super(title, itemDescription, contactNum, contactEmail, password, price, campus);
        this.course = course;
    }

    public void edit(String title, String itemDescription, String contactNum, String contactEmail, String password,
                     double price, Item.campus campus, String course) {
        // Polymorphed method from superclass, edits all attributed to the ones outlined in parameters
        super.edit(title, itemDescription, contactNum, contactEmail, password, price, campus);
        setCourse(course);
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + " - for " + this.getCourse() + ", Price: " + this.getPrice() + ", Description: " + this.getItemDescription() +
                System.lineSeparator() + ", Contact info: " + this.getContactEmail() + " / " + this.getContactNum() + ", available at the " + this.getCampus() + " campus.";
    }

    // Getters and setters

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
