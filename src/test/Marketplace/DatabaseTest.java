//package Marketplace;
//
//import Marketplace.Items.types.Clothes;
//import Marketplace.Items.types.Item;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class DatabaseTest {
//
//    @Before
//    public void setUpItems(){
//        ItemManager itemManager = new ItemManager();
//        String pass = "123";
//        String contact = "12345";
//        String email = "123@gmail.com";
//        itemManager.CreatePostAnimal("Cat", "Furry", 105, contact,
//                pass, email, Item.campus.UTSG, "Cheetah");
//        itemManager.CreatePostClothes("Tshirt", "Red", 10, contact,
//                pass, email, Item.campus.UTSG, Clothes.size.S, Item.condition.LikeNew);
//        itemManager.CreatePostElectronic("Laptop", "Mac", 500, contact,
//                pass, email, Item.campus.UTSC, Item.condition.LikeNew, "Mac Pro");
//        itemManager.CreatePostMisc("cup", "blue", 2, contact,
//                pass, email, Item.campus.UTSG);
//        itemManager.CreatePostTextbook("Math", "MAT123 textbook", 10, contact,
//                pass, email, Item.campus.UTM, "Algebra");
//    }
//    @Test(timeout = 5000)
//    public void testGetLst(){
//        ArrayList<Item> items = Database.GetLst();
//        assert (!items.isEmpty());
//        assert (items.size() == 5);
//        assert (Objects.equals(items.get(0).getName(), "Cat"));
//    }
//}