package java;



import org.junit.Test;

import Marketplace.Item;

import static org.junit.Assert.assertEquals;

public class ItemTest {



    @Test(timeout = 50)
    public void testgetName() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getName(), "cat");

    }
    @Test(timeout = 50)
    public void testgetItem_description() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getItem_description(), "furry animal");

    }
    @Test(timeout = 50)
    public void testgetContact_num() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getContact_num(), "7689503921");

    }
    @Test(timeout = 50)
    public void testgetContact_email() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getContact_email(), "j@gmail.com");

    }
    @Test(timeout = 50)
    public void testgetPassword() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getPassword(), "123");

    }
    @Test(timeout = 50)
    public void testgetPrice() {
        Item sample = new Item("cat", "furry animal", "7689503921",
                "j@gmail.com", "123", 12.0);

        assertEquals(sample.getPrice(), "12.0");

    }




}