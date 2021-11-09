package Marketplace.Items.types;

public class Animal extends Item{
    private String animal_type;

    public Animal(){
        super();
        this.animal_type = null;
    }

    public Animal(String title, String item_description, String contact_num, String contact_email, String password,
                   double price, Item.campus campus, String animal_type) {
        super(title, item_description, contact_num, contact_email, password, price, campus);
        this.animal_type = animal_type;
    }

    public void edit(String title, String item_description, String contact_num, String contact_email, String password,
                     double price, Item.campus campus, String animal_type) {
        super.edit(title, item_description, contact_num, contact_email, password, price, campus);
        setAnimal_type(animal_type);
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }
}
