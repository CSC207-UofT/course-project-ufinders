package Marketplace;

import Marketplace.Items.types.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MarketplaceWindow {

    /**
     * displays the given prompts and buttons for the given options
     * @param prompt the prompt for the user
     * @param options a list of Strings, with each String being one button
     * @return the user's response to the prompt
     */
    public String getChoice(String prompt, String[] options) {
        int response = JOptionPane.showOptionDialog(null, prompt, null, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, "");
        return options[response];
    }

    /**
     * gets the users input for a certain prompt
     * @param prompt the prompt for the user
     * @return the user's response to the prompt
     */
    public String getInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt,
                null, JOptionPane.QUESTION_MESSAGE);
    }


    /**
     * Displays the list of items that are on sale on popup window
     */

    public void displayItems(ArrayList<Item> items) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JFrame jFrame = new JFrame();
        //       this closes the whole program so, we have to fix it to go somewhere else
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Done");
        int curr_item = 0;
        while (curr_item < items.size()) {
            listModel.addElement((curr_item + 1) + ". " + items.get(curr_item).getName() + ", for $" +
                    items.get(curr_item).getPrice());
            curr_item++;
        }
        JList<String> jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int ind = jList.getSelectedIndex();
                button.setVisible(true);
                displayItemInfo(items.get(ind));
            }
        });
        button.addActionListener(e -> {
            jFrame.setVisible(false);
            MarketplaceUI.intro();
        });
        jList.setBounds(0, 0, 700, 700);
        button.setBounds(350, 600, 60, 30);
        jFrame.add(button);
        jFrame.add(jList);
        jFrame.pack();
        jFrame.setSize(700, 700);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setVisible(true);


    }


    /**
     * Helper method for displayItems
     * Displays the item's information on new popup window when the item is clicked on in displayItems
     */
    private static void displayItemInfo(Item item) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText(item.toString());
        frame.setSize(1000, 300);
        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * displays information to the user
     * @param info information to be displayed
     */
    public void displayInfo(String info) {
        JOptionPane.showOptionDialog(null, info, null, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Next"}, "");
    }


}