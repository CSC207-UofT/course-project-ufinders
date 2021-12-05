package Marketplace;

import Marketplace.Items.types.*;
import org.junit.Test;


public class ItemManagerTest {
    ItemManager itemManager = new ItemManager();
    String pass = "123";
    String contact = "12345";
    String email = "123@gmail.com";


    /**
     * Check to see the items are stored
     */
    @Test(timeout = 1000)
    public void testCreatePosts(){
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
        assert (!Database.item_type_lst.isEmpty());
    }


    /**
     * test if password matches
     */
    @Test(timeout = 500)
    public void testPasswordMatch(){
        itemManager.CreatePostElectronic("Laptop", "Mac", 500, contact,
                pass, email, Item.campus.UTSC, Item.condition.LikeNew, "Mac Pro");
        assert (!ItemManager.password_match("", ""));
        assert (!ItemManager.password_match("laptop", "1234"));
        assert (!ItemManager.password_match("phone", "123"));
    }

    /**
     * test that removePost can remove item and that only the item that needs to be removed is removed
     */
    @Test(timeout = 500)
    public void testRemovePost(){
        Electronic phone = new Electronic();
        phone.edit("phone", "shiny", "123", "123", "123", 1000,
                Item.campus.UTM, Item.condition.LikeNew, "iphone");
        Database.StoreItem(phone);
        assert (!ItemManager.removePost("laptop", "123"));
        assert (ItemManager.removePost("phone", "123"));
        assert (!ItemManager.removePost("phone", "123"));
    }



}