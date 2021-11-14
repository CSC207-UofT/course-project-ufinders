package Marketplace;

import Marketplace.Items.types.*;
import Marketplace.comparators.HighPrice;
import Marketplace.comparators.LowPrice;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class SorterTest {
    public static Animal animal1 = new Animal();
    public static Textbook textbook1 = new Textbook();
    public static Textbook textbook2 = new Textbook();
    public static Textbook textbook3 = new Textbook();
    public static ArrayList<Item> allitems;
    public static ArrayList<Item> noitems = new ArrayList<>();

    @Before
    public void setUp() {
        allitems = new ArrayList<>();
        animal1.edit("cat for sale", "long-haired cat with green eyes", "1234", "cats" +
                "@gmail.com", "password", 30, Item.campus.UTSG, "cat");
        textbook1.edit("ECO105 textbook", "microeconomics for life, like new", "1234", "cats" +
                "@gmail.com", "password", 15, Item.campus.UTSG, "ECO105");
        textbook2.edit("ECO105 textbook", "macroeconomics for life", "1234", "cats" +
                "@gmail.com", "password", 10, Item.campus.UTSG, "ECO105");
        textbook3.edit("ENV333 course packet", "Printed out course packet, used", "1234", "cats" +
                "@gmail.com", "password", 40, Item.campus.UTSC, "ENV333");
        allitems.add(animal1);
        allitems.add(textbook1);
        allitems.add(textbook2);
        allitems.add(textbook3);

    }

    @Test(timeout = 1000)
    public void testHighLowSorting(){
        Sorter sorter = new Sorter(new LowPrice());
        sorter.sort(allitems);
        assert allitems.size() == 4;
        assert allitems.get(0).equals(textbook3);
        assert allitems.get(1).equals(animal1);
        assert allitems.get(2).equals(textbook1);
        assert allitems.get(3).equals(textbook2);
    }

    @Test(timeout = 1000)
    public void testLowHighSorting(){
        Sorter sorter = new Sorter(new HighPrice());
        sorter.sort(allitems);
        assert allitems.size() == 4;
        assert allitems.get(3).equals(textbook3);
        assert allitems.get(2).equals(animal1);
        assert allitems.get(1).equals(textbook1);
        assert allitems.get(0).equals(textbook2);
    }

    @Test(timeout = 1000)
    public void testEmptySorting(){
        Sorter sorter = new Sorter(new HighPrice());
        sorter.sort(noitems);
        assert noitems.isEmpty();
    }

}
