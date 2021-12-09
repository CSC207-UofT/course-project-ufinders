package Marketplace;

import Marketplace.Items.types.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarketplaceControllerTest {

    /**
     * test that animals can be made
     */
    @Test(timeout = 5000)
    public void testMakeAnimal(){
        String[] info = new String[]{"cat", "furry", "20", "1234567", "email@email.com", "password", "UTSG", "cat"};
        ArrayList<String> animalinfo = new ArrayList<>(List.of(info));
        MarketplaceController.sellingInfo("Animal", animalinfo);
        assert (!Database.item_type_lst.isEmpty());
    }
    /**
     * test that clothes can be made
     */
    @Test(timeout = 5000)
    public void testMakeClothes(){
        String[] info = new String[]{"cat", "furry", "20", "1234567", "email@email.com", "password", "UTSG", "New", "XS"};
        ArrayList<String> animalinfo = new ArrayList<>(List.of(info));
        MarketplaceController.sellingInfo("Clothing", animalinfo);
        assert (!Database.item_type_lst.isEmpty());
    }

    /**
     * test that electronics can be made
     */
    @Test(timeout = 5000)
    public void testMakeElectronic(){
        String[] info = new String[]{"cat", "furry", "20", "1234567", "email@email.com",
                "password", "UTSG", "New", "good"};
        ArrayList<String> animalinfo = new ArrayList<>(List.of(info));
        MarketplaceController.sellingInfo("Electronic", animalinfo);
        assert (!Database.item_type_lst.isEmpty());
    }

    /**
     * test that misc items can be made
     */
    @Test(timeout = 5000)
    public void testMakeMisc(){
        String[] info = new String[]{"cat", "furry", "20", "1234567", "email@email.com", "password", "UTSG"};
        ArrayList<String> animalinfo = new ArrayList<>(List.of(info));
        MarketplaceController.sellingInfo("Other", animalinfo);
        assert (!Database.item_type_lst.isEmpty());
    }

    /**
     * test that textbooks can be made
     */
    @Test(timeout = 5000)
    public void testMakeTextbook(){
        String[] info = new String[]{"cat", "furry", "20", "1234567", "email@email.com", "password", "UTSG", "ENV333"};
        ArrayList<String> animalinfo = new ArrayList<>(List.of(info));
        MarketplaceController.sellingInfo("Textbook", animalinfo);
        assert (!Database.item_type_lst.isEmpty());
    }

    /**
     * test that startSearch successfully executes searches
     */
    @Test(timeout = 5000)
    public void testMakeSearch(){
        ItemManager manager = new ItemManager();
        manager.CreatePostAnimal("cat for sale", "long-haired cat with green eyes", 20, "cats" +
                "@gmail.com", "password", "clove", Item.campus.UTSG, "cat");
        manager.CreatePostTextbook("ECO105 textbook", "microeconomics for life, like new", 15, "cats" +
                "@gmail.com", "password", "clove", Item.campus.UTSG, "ECO105");
        manager.CreatePostClothes("Shirt", "ramen-themed shirt", 50, "cats" +
                "@gmail.com", "password", "clove", Item.campus.UTM, Clothes.size.L, Item.condition.LikeNew);
        manager.CreatePostClothes("Pants", "ramen-themed sweatpants (they go great with my ramen-themed shirt!)", 500, "cats" +
                "@gmail.com", "password", "clove", Item.campus.UTM, Clothes.size.L, Item.condition.New);
        manager.CreatePostMisc("rug", "old rug, moving out so pick up soon", 35, "cats" +
                "@gmail.com", "password", "clove", Item.campus.UTSG);
        HashMap<String, String> info = new HashMap<>();
        info.put("campus", "UTSG");
        info.put("lowprice", "10");
        info.put("highprice", "20");
        ArrayList<Item> results = MarketplaceController.startSearch(info);
        assert results.size() == 1;
        assert results.get(0) instanceof Textbook;
    }

    /**
     * test that removePost can successfully remove posts and return false when needed
     */
    @Test(timeout = 500)
    public void testRemovePost(){
        Electronic phone = new Electronic();
        phone.edit("phone", "shiny", "123", "123", "123", 1000,
                Item.campus.UTM, Item.condition.LikeNew, "iphone");
        Database.StoreItem(phone);
        assert (!MarketplaceController.removePost("laptop", "123"));
        assert (MarketplaceController.removePost("phone", "123"));
        assert (!MarketplaceController.removePost("phone", "123"));
    }
}
