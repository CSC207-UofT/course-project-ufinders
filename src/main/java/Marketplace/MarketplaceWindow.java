package Marketplace;

import Marketplace.Items.types.Item;

import javax.swing.*;
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

    public void displayItems(ArrayList<Item> items){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JFrame jFrame = new JFrame();
        //       this closes the whole program so, we have to fix it to go somewhere else
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int curr_item = 0;
        while(curr_item < items.size()){
            listModel.addElement((curr_item + 1) + ". " + items.get(curr_item).getName()  + ", for $" +
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
        jFrame.setLayout(null);
        jFrame.setVisible(true);


    }


    /**
     * Helper method for displayItems
     * Displays the item's information on new popup window when the item is clicked on in displayItems
     */
    private static void displayItemInfo(Item item){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText(item.toString());
        frame.setSize(400, 300);
        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

        public void displayInfo(String info){
            JOptionPane.showOptionDialog(null, info, null, JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new String[]{"Next"}, "");
    }

}
