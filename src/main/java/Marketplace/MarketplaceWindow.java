package Marketplace;

import Marketplace.Items.types.Animal;
import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Electronic;
import Marketplace.Items.types.Item;

import javax.swing.*;
import java.util.ArrayList;

public class MarketplaceWindow {

    public String getChoice(String prompt, String[] options) {
        int response =  JOptionPane.showOptionDialog(null, prompt, null, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, "");
        return options[response];
    }

    public String getInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt,
                null, JOptionPane.QUESTION_MESSAGE);
    }

    public static void displayItems(ArrayList<Item> items){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JFrame jFrame = new JFrame();
        int curr_item = 0;
        while(curr_item < items.size()){
            listModel.addElement((curr_item + 1) + ". " + items.get(curr_item).getName() + ": " +
                            items.get(curr_item).getItem_description() + ", for $" +
                    items.get(curr_item).getPrice());
            curr_item++;
        }
        JList<String> jList = new JList<>(listModel);
        jList.setBounds(0, 0, 400, 400);
        jFrame.add(jList);
        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setVisible(true);


    }

    public static void displayInfo(String info){
        JOptionPane.showConfirmDialog(null, info);
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.edit("Apple", "35y/o", "123", "123", "123",
                100, Item.campus.UTSG, "horse");
        Electronic electronic = new Electronic();
        electronic.edit("Banana", "product", "123", "123", "123",
                1000, Item.campus.UTSC, Item.condition.LikeNew, "Phone");
        Clothes clothes = new Clothes();
        clothes.edit("Bright", "colorful", "123", "123", "123",
                12, Item.campus.UTM, Clothes.size.S, Item.condition.New);
        Database.StoreItem(animal);
        Database.StoreItem(electronic);
        Database.StoreItem(clothes);
        ItemManager.remove_post("Apple", "123");
        displayItems(Database.GetLst());
    }

}

