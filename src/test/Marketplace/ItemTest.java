package Marketplace;

import Marketplace.Items.ItemCreator;
import Marketplace.Items.types.*;
import org.junit.Test;

public class ItemTest {
    ItemCreator ic = new ItemCreator();

    @Test(timeout = 5000)
    public void testInstance(){
        Item misc = ic.makeItem(ItemCategories.misc);
        assert (misc instanceof Misc);
    }

    @Test(timeout = 5000)
    public void testEmpty(){
        Item clothes = ic.makeItem(ItemCategories.clothes);
        assert (clothes.getName() == null);
    }

    @Test(timeout = 5000)
    public void testEdit(){
        Textbook textbook = (Textbook) ic.makeItem(ItemCategories.textbook);
        textbook.edit("MAT137 Textbook", "Textbook for class", "12345",
                "abc@email.com", "123", 10.0, Item.campus.UTSC, "MAT137");
        assert (textbook.getName().equals("MAT137 Textbook"));
        assert (textbook.getPrice() == 10.0);
        assert (textbook.getCampus().equals(Item.campus.UTSC));
        assert (textbook.getCourse().equals("MAT137"));
        assert (textbook.getItem_description().equals("Textbook for class"));
        assert (textbook.getPassword().equals("123"));
        assert (textbook.getContact_email().equals("abc@email.com"));
        assert (textbook.getContact_num().equals("12345"));
    }

    @Test(timeout = 5000)
    public void testIDs(){
        Item electronic = ic.makeItem(ItemCategories.electronics);
        Item animal = ic.makeItem(ItemCategories.animal);
        assert (electronic.getId() != animal.getId());
    }

    @Test(timeout = 5000)
    public void testtoString(){
        Animal animal = (Animal) ic.makeItem(ItemCategories.animal);
        animal.edit("Dog woof woof", "A loyal companion", "12345",
                "abc@email.com", "123", 10.0, Item.campus.UTSG, "Dog");
        String animalstring = animal.toString();
        assert(animalstring.equals("Name: Dog woof woof - Dog, Price: 10.0, Description: A loyal companion, Contact info: abc@email.com / 12345," +
                " available at the UTSG campus."));
    }
}
