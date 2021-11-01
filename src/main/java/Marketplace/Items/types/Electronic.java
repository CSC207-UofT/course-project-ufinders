package Marketplace.Items.types;

public class Electronic extends Item {

    private Item.condition condition;
    private String tech_specifications;

    public Electronic(){
        super();
        this.condition = null;
        this.tech_specifications = null;
    }

    public Electronic(String title, String item_description, String contact_num, String contact_email, String password,
                      double price, Item.campus campus, Item.condition condition, String tech_specifications) {
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.condition = condition;
        this.tech_specifications = tech_specifications;
    }

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
