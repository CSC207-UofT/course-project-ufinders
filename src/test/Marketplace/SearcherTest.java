package Marketplace;

import Marketplace.Items.types.*;
import Marketplace.comparators.HighPrice;
import Marketplace.filters.campusFilter;
import Marketplace.filters.priceFilter;
import Marketplace.filters.typeFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class SearcherTest {
    public static ItemManager manager = new ItemManager();

    @BeforeClass
    public static void setUp() {
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
    }

    @Test(timeout = 1000)
    public void testSearcherOneFilter(){
        campusFilter filter = new campusFilter(Item.campus.UTM);
        Searcher searcher = new Searcher();
        searcher.addFilter(filter);
        ArrayList<Item> results = searcher.execute();
        assert results.size() == 2;
        assert results.get(0) instanceof Clothes;
        assert results.get(1) instanceof Clothes;
    }

    @Test(timeout = 1000)
    public void testSearcherMultiFilters(){
        campusFilter filter1 = new campusFilter(Item.campus.UTSG);
        priceFilter filter2 = new priceFilter(10, 20);
        Searcher searcher = new Searcher();
        searcher.addFilter(filter1);
        searcher.addFilter(filter2);
        ArrayList<Item> results = searcher.execute();
        assert results.size() == 1;
        assert results.get(0) instanceof Animal;

    }

    @Test(timeout = 1000)
    public void testSearcherwithSorter(){
        campusFilter filter = new campusFilter(Item.campus.UTSG);
        Searcher searcher = new Searcher();
        searcher.addFilter(filter);
        Sorter sorter = new Sorter(new HighPrice());
        searcher.addSorter(sorter);
        ArrayList<Item> results = searcher.execute();
        assert results.size() == 3;
        assert results.get(0) instanceof Misc;
        assert results.get(2) instanceof Textbook;
    }

    @Test(timeout = 1000)
    public void testSearcherNoResultss(){
        campusFilter filter1 = new campusFilter(Item.campus.UTSG);
        typeFilter filter2 = new typeFilter(ItemCategories.clothes);
        Searcher searcher = new Searcher();
        searcher.addFilter(filter1);
        searcher.addFilter(filter2);
        ArrayList<Item> results = searcher.execute();
        assert results.isEmpty();

    }

}
