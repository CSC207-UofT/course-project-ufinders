package Marketplace;

import java.util.ArrayList;

public class Sorter {

    private String direction = "high-low";
    private String type = "time";

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean correctorder(Item item1, Item item2) {
        if (this.type.equals("price") && this.direction.equals("high-low")) {
            return item1.getPrice() > item2.getPrice();
        } else if (this.type.equals("price") && this.direction.equals("low-high")) {
            return item1.getPrice() < item2.getPrice();
        }
        return false;
    }

    public ArrayList<Item> sort(ArrayList<Item> tosort) {
        if (tosort.size() < 2) {
            return tosort;
        } else {
            int mid = tosort.size() / 2;
            ArrayList<Item> left_sorted = sort((ArrayList<Item>) tosort.subList(0, mid));
            ArrayList<Item> right_sorted = sort((ArrayList<Item>) tosort.subList(mid, 0));
            return this.merge(left_sorted, right_sorted);
        }
    }

    private ArrayList<Item> merge(ArrayList<Item> lst1, ArrayList<Item> lst2) {
        int index1 = 0;
        int index2 = 0;
        ArrayList<Item> merged = new ArrayList<>();

        while (index1 < lst1.size() && index2 < lst2.size()){
            if (correctorder(lst1.get(index1), lst2.get(index2))){
                merged.add(lst1.get(index1));
                index1 += 1;
            }
            else {
                merged.add(lst2.get(index2));
                index2 += 1;
            }
        }
        merged.addAll(lst1.subList(index1, lst1.size()));
        merged.addAll(lst2.subList(index2, lst2.size()));
        return merged;
    }
}

