package Marketplace;

import Marketplace.Items.types.Animal;
import Marketplace.Items.types.Clothes;
import Marketplace.Items.types.Electronic;
import Marketplace.Items.types.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
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

    /**
     * Displays the list of items that are on sale on popup window
     */
    public static void displayItems(ArrayList<Item> items){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JFrame jFrame = new JFrame();
//       this closes the whole program so we have to fix it to go somewhere else
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int curr_item = 0;
        while(curr_item < items.size()){
            listModel.addElement((curr_item + 1) + ". " + items.get(curr_item).getName() + ", for $" +
                    items.get(curr_item).getPrice());
            curr_item++;
        }
        JList<String> jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int ind = jList.getSelectedIndex();
                displayItemInfo(items.get(ind));
            }
        });
        jList.setBounds(0, 0, 700, 700);
        jFrame.add(jList);
        jFrame.setSize(700,700);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


    }

    /**
     * Displays the item's information on new popup window when the item is clicked on in displayItems
     */
    private static void displayItemInfo(Item item){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText(item.getName());
        frame.setSize(500, 300);
        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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

