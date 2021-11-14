package Marketplace;

import Marketplace.Items.types.*;
import Marketplace.filters.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class FiltersTest {
    public static ArrayList<Item> noitems;
    public static ArrayList<Item> allitems;
    public static ArrayList<Item> textbooks;
    public static ArrayList<Item> clothes;
    public static Animal animal1 = new Animal();
    public static Textbook textbook1 = new Textbook();
    public static Textbook textbook2 = new Textbook();
    public static Textbook textbook3 = new Textbook();
    public static Clothes clothes1 = new Clothes();
    public static Clothes clothes2 = new Clothes();
    public static Clothes clothes3 = new Clothes();
    public static Misc misc1 = new Misc();
    public static Electronic electronic1 = new Electronic();

    @BeforeClass
    public static void setUp() {
        animal1.edit("cat for sale", "long-haired cat with green eyes", "1234", "cats" +
                "@gmail.com", "password", 20, Item.campus.UTSG, "cat");
        textbook1.edit("ECO105 textbook", "microeconomics for life, like new", "1234", "cats" +
                "@gmail.com", "password", 15, Item.campus.UTSG, "ECO105");
        textbook2.edit("ECO105 textbook", "macroeconomics for life", "1234", "cats" +
                "@gmail.com", "password", 15, Item.campus.UTSG, "ECO105");
        textbook3.edit("ENV333 course packet", "Printed out course packet, used", "1234", "cats" +
                "@gmail.com", "password", 20, Item.campus.UTSC, "ENV333");
        clothes1.edit("Shirt", "ramen-themed shirt", "1234", "cats" +
                "@gmail.com", "password", 50, Item.campus.UTM, Clothes.size.L, Item.condition.LikeNew);
        clothes2.edit("Pants", "ramen-themed sweatpants (they go great with my ramen-themed shirt!)", "1234", "cats" +
                "@gmail.com", "password", 100, Item.campus.UTM, Clothes.size.L, Item.condition.New);
        clothes3.edit("Red hat", "hat, bright red, no words", "1234", "cats" +
                "@gmail.com", "password", 5, Item.campus.UTSC, Clothes.size.S, Item.condition.Used);
        misc1.edit("rug", "old rug, moving out so pick up soon", "1234", "cats" +
                "@gmail.com", "password", 35, Item.campus.UTSG);
        electronic1.edit("iPhone", "iPhone 1000 for sale, buy asap", "1234", "cats" +
                "@gmail.com", "password", 1000, Item.campus.UTM, Item.condition.New, "look them up!" +
                " Note: it exclusively works with 30G");
        allitems.add(animal1);
        allitems.add(textbook1);
        allitems.add(textbook2);
        allitems.add(textbook3);
        allitems.add(clothes1);
        allitems.add(clothes2);
        allitems.add(clothes3);
        allitems.add(misc1);
        allitems.add(electronic1);
        clothes.add(clothes1);
        clothes.add(clothes2);
        clothes.add(clothes3);
        textbooks.add(textbook1);
        textbooks.add(textbook2);
        textbooks.add(textbook3);
    }

    @Test(timeout = 1000)
    public void testCampusEmptyList(){
        campusFilter filter = new campusFilter(Item.campus.UTSG);
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testCampusMediumList(){
        campusFilter filter = new campusFilter(Item.campus.UTSC);
        ArrayList<Item> results = filter.apply(clothes);
        assert results.size() == 1;
        assert results.contains(clothes3);
    }

    @Test(timeout = 1000)
    public void testCampusLargeList(){
        campusFilter filter = new campusFilter(Item.campus.UTSG);
        ArrayList<Item> results = filter.apply(clothes);
        assert results.size() == 4;
        assert results.contains(animal1);
        assert results.contains(textbook1);
        assert results.contains(textbook2);
        assert results.contains(misc1);
    }

    @Test(timeout = 1000)
    public void testConditionEmptyList(){
        conditionFilter filter = new conditionFilter(Item.condition.LikeNew);
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testConditionMediumList(){
        conditionFilter filter = new conditionFilter(Item.condition.Used);
        ArrayList<Item> results = filter.apply(clothes);
        assert results.size() == 1;
        assert results.contains(clothes3);
    }

    @Test(timeout = 1000)
    public void testConditionLargeList(){
        conditionFilter filter = new conditionFilter(Item.condition.New);
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 2;
        assert results.contains(clothes2);
        assert results.contains(electronic1);
    }

    @Test(timeout = 1000)
    public void testCourseEmptyList(){
        courseFilter filter = new courseFilter("ECO105");
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testCourseMediumList(){
        courseFilter filter = new courseFilter("ECO105");
        ArrayList<Item> results = filter.apply(textbooks);
        assert results.size() == 2;
        assert results.contains(textbook2);
        assert results.contains(textbook1);
    }

    @Test(timeout = 1000)
    public void testCourseLargeList(){
        courseFilter filter = new courseFilter("ENV333");
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 1;
        assert results.contains(textbook3);
    }

    @Test(timeout = 1000)
    public void testPriceEmptyList(){
        priceFilter filter = new priceFilter(10, 35);
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testPriceMediumList(){
        priceFilter filter = new priceFilter(10, 18);
        ArrayList<Item> results = filter.apply(textbooks);
        assert results.size() == 2;
        assert results.contains(textbook1);
        assert results.contains(textbook2);
    }

    @Test(timeout = 1000)
    public void testPriceLargeList(){
        priceFilter filter = new priceFilter(30, 150);
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 3;
        assert results.contains(clothes2);
        assert results.contains(clothes1);
        assert results.contains(misc1);
    }

    @Test(timeout = 1000)
    public void testSizeEmptyList(){
        sizeFilter filter = new sizeFilter(Clothes.size.M);
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testSizeMediumList(){
        sizeFilter filter = new sizeFilter(Clothes.size.S);
        ArrayList<Item> results = filter.apply(clothes);
        assert results.size() == 1;
        assert results.contains(clothes3);
    }

    @Test(timeout = 1000)
    public void testSizeLargeList(){
        sizeFilter filter = new sizeFilter(Clothes.size.L);
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 2;
        assert results.contains(clothes2);
        assert results.contains(clothes1);
    }

    @Test(timeout = 1000)
    public void testTypeEmptyList(){
        typeFilter filter = new typeFilter(ItemCategories.animal);
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testTypeMediumList(){
        typeFilter filter = new typeFilter(ItemCategories.clothes);
        ArrayList<Item> results = filter.apply(textbooks);
        assert results.isEmpty();
    }

    @Test(timeout = 1000)
    public void testTypeLargeList(){
        typeFilter filter = new typeFilter(ItemCategories.misc);
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 1;
        assert results.contains(misc1);
    }

    @Test(timeout = 1000)
    public void testWordEmptyList(){
        wordFilter filter = new wordFilter("cat");
        assert filter.apply(noitems).isEmpty();
    }

    @Test(timeout = 1000)
    public void testWordMediumList(){
        wordFilter filter = new wordFilter("ramen");
        ArrayList<Item> results = filter.apply(clothes);
        assert results.size() == 2;
        assert results.contains(clothes1);
        assert results.contains(clothes2);
    }

    @Test(timeout = 1000)
    public void testWordLargeList(){
        wordFilter filter = new wordFilter("cat");
        ArrayList<Item> results = filter.apply(allitems);
        assert results.size() == 1;
        assert results.contains(animal1);
    }

}