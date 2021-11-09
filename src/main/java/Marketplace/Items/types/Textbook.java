package Marketplace.Items.types;

public class Textbook extends Item{
    private String course;

    public Textbook(){
        super();
        this.course = null;
    }

    public Textbook(String title, String item_description, String contact_num, String contact_email, String password,
                      double price, Item.campus campus, String course) {
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.course = course;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, Item.campus campus, String course) {
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setCourse(course);
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
