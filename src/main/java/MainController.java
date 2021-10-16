package java;

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
            if (choice.equals("market")) {
                User_controls.intro();
            } else if (choice.equals("self_help")) {
                new User.User_UI();
            } else if (choice.equals("exit")) {
                stop = true;
            }
        }
    }
}
