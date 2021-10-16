package codestuff;

import codestuff.Marketplace.User_Controls;
import codestuff.User.User_UI;

import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {
        System.out.println("HELLO");
        menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("HELLO");
        boolean stop = false;
        while (!stop) {
            System.out.println("Welcome to Ufind! Would you like to enter the self-help section, enter the marketplace, or exit? (selfhelp/market/exit)");
            String choice = input.nextLine();
            if (choice.equals("market")) {
                User_Controls.intro();
            } else if (choice.equals("selfhelp")) {
                System.out.println("on our way");
                User_UI.beginning();
            } else if (choice.equals("exit")) {
                stop = true;
            }
        }
    }
}