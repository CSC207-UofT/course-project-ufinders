package Main;

import Marketplace.User_Controls;
import User.User_UI;

import java.io.IOException;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.println("Welcome to Ufind! Would you like to enter the self_help , enter the market, or exit? (self-help/market/exit)");
            String choice = input.nextLine();
            switch (choice) {
                case "market":
                    User_Controls.intro();
                    break;
                case "self_help":
                    String[] arguments = new String[]{"sample"};
                    User_UI.main(arguments);
                    break;
                case "exit":
                    stop = true;
                    break;
            }
        }
    }
}
