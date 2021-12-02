package Marketplace;

import javax.swing.*;

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

    public void displayInfo(String info){
        JOptionPane.showConfirmDialog(null, info);
    }
}

