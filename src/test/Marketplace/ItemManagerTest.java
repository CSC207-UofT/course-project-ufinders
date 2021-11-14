package Marketplace;

import Marketplace.Items.types.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ItemManagerTest {
    ItemManager itemManager = new ItemManager();
    String pass = "123";
    String contact = "12345";
    String email = "123@gmail.com";

    @Test(timeout = 1000)
    public void testCreatePosts(){
        itemManager.CreatePostAnimal("Cat", "Furry", 105, contact,
                pass, email, Item.campus.UTSG, "Cheetah");
        itemManager.CreatePostClothes("Tshirt", "Red", 10, contact,
                pass, email, Item.campus.UTSG, Clothes.size.S, Item.condition.LikeNew);
        itemManager.CreatePostElectronic("Laptop", "Mac", 500, contact,
                pass, email, Item.campus.UTSC, Item.condition.LikeNew, "Mac Pro");
        itemManager.CreatePostMisc("cup", "blue", 2, contact,
                pass, email, Item.campus.UTSG);
        itemManager.CreatePostTextbook("Math", "MAT123 textbook", 10, contact,
                pass, email, Item.campus.UTM, "Algebra");
        ArrayList<Item> lst = Database.GetLst();
        assert (lst.get(0) instanceof Animal);
        assert (lst.get(1) instanceof Clothes);
        assert (lst.get(2) instanceof Electronic);
        assert (lst.get(3) instanceof Misc);
        assert (lst.get(4) instanceof Textbook);
    }


//    @Test(timeout = 500)
//    public void testpassword_match(){
//        itemManager.CreatePostElectronic("Phone", "Mac", 500, contact,
//                pass, email, Item.campus.UTSC, Item.condition.LikeNew, "Mac Pro");
//        itemManager.CreatePostMisc("Bowl", "blue", 2, contact,
//                pass, email, Item.campus.UTSG);
//        ArrayList<Item> items = Database.GetLst();
//        assert (!items.isEmpty());
//        assert ItemManager.password_match("Phone", "123");
//    }



}