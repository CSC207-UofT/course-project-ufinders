package java;

import java.Marketplace.*;
import java.User.*;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.println("Welcome to Ufind! Would you like to enter the self-help section, enter the marketplace, or exit? (self-help/market/exit)");
            String choice = input.nextLine();
            switch (choice) {
                case "market":
                    User_Controls.intro();
                    break;
                case "self_help":
                    new User_UI();
                    break;
                case "exit":
                    stop = true;
                    break;
            }
        }
    }
}
