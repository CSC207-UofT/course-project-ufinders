package Marketplace.Items.types;

public class Clothes extends Item{
    enum size{
        XS, S, M, L, XL, XXL
    }
    private size size;
    private condition condition;

    public Clothes(){
        super();
        this.size = null;
        this.condition = null;
    }

    public Clothes(String title, String item_description, String contact_num, String contact_email, String password,
                    double price, Item.campus campus, size size, condition condition) {
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.size = size;
        this.condition = condition;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, Item.campus campus, size size, condition condition) {
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setSize(size);
        setCondition(condition);
    }

    public size getSize() {
        return size;
    }

    public void setSize(size size) {
        this.size = size;
    }
}
