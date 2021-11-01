package Marketplace;

import java.util.ArrayList;

public class Searching {
    private ArrayList<String> campus;
    private int highprice;
    private int lowprice;
    private Sorter sorter;
    private boolean priceset;
    private String keyword;

    public Searching() {
        priceset = false;
        campus = new ArrayList<>();
        campus.add("UTSG");
        campus.add("UTSC");
        campus.add("UTM");
        sorter = new Sorter();
    }

    public void execute() {
        Iterable<Item> items = Database.GetLst();
        ArrayList<Item> filtered = new ArrayList<>();
        for (Item i: items){
            if (this.acceptable(i)){
                filtered.add(i);
            }
        }
        ArrayList<Item> result = this.sorter.sort(filtered);
        System.out.println(result);
    }

    private boolean acceptable(Item i){
        if (priceset){
            if (i.getPrice()<this.lowprice||i.getPrice()>this.highprice){
                return false;
            }
        }
        if (!i.getItem_description().contains(this.keyword) && !i.getName().contains(this.keyword)){
            return false;
        }
        return true;
    }

    public void setPrices(String lowprice, String highprice) {
        this.highprice = Integer.parseInt(highprice);
        this.lowprice = Integer.parseInt(lowprice);
        this.priceset = true;
    }

    public void setSortchoice(String direction, String type) {
        this.sorter.setDirection(direction);
        this.sorter.setType(type);
    }

    public void setCampus(String campus) {
        this.campus = new ArrayList<>();
        this.campus.add(campus);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}