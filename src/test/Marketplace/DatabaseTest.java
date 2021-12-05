package Marketplace;

import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Electronic;
import Marketplace.Items.types.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DatabaseTest {

    @Before
    public void setUpItems(){
        ItemManager itemManager = new ItemManager();
        String pass = "123";
        String contact = "12345";
        String email = "123@gmail.com";
        itemManager.CreatePostAnimal("Cat", "Furry", 105, contact,
                pass, email, Item.campus.UTSG, "Cheetah");
        itemManager.CreatePostClothes("Shirt", "Red", 10, contact,
                pass, email, Item.campus.UTSG, Clothes.size.S, Item.condition.LikeNew);
        itemManager.CreatePostElectronic("Laptop", "Mac", 500, contact,
                pass, email, Item.campus.UTSC, Item.condition.LikeNew, "Mac Pro");
        itemManager.CreatePostMisc("cup", "blue", 2, contact,
                pass, email, Item.campus.UTSG);
        itemManager.CreatePostTextbook("Math", "MAT123 textbook", 10, contact,
                pass, email, Item.campus.UTM, "Algebra");
    }

    /**
     * test that items can be retrieved
     */
    @Test(timeout = 5000)
    public void testGetLst(){
        ArrayList<Item> items = Database.GetLst();
        assert (!items.isEmpty());
    }


    @Test(timeout = 500)
    public void testDeleteItem(){
        Electronic phone = new Electronic();
        phone.edit("phone", "shiny", "123", "123", "123", 1000,
                Item.campus.UTM, Item.condition.LikeNew, "iphone");
        Database.StoreItem(phone);
        int len = Database.GetLst().size();
        Database.DeleteItem(0);
        assert (Database.GetLst().size() == len - 1);
    }


}