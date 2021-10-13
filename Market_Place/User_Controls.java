import java.util.Objects;
import java.util.Scanner;

public class User_Controls {

    public static void main(String[] args) {
        intro();
    }
    public static void intro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to buy or sell? (Write 'buy' or 'sell'");
        String segmentchoice = input.nextLine();
        if (Objects.equals(segmentchoice, "buy")){
            buying_info();
        }
        else if (Objects.equals(segmentchoice, "sell")){
            selling_info();
        }
        else {
            intro();
        }
    }

    private static void selling_info(){
        String name = get_input("What is the name of the item you're selling?");
        String description = get_input("What is the description of the item you're selling?");
        String price = get_input("What is the price of the item you're selling?");
        String contact = get_input("What is your contact information?");
        String password = get_input("What password would you like to use to delete this post after the item is sold?");
        Double price1 = Double.parseDouble(price);
        Sell_Buy sell_buy = new Sell_Buy();
        sell_buy.create_post(name, description, price1, contact, password);
    }

    private static void buying_info(){
        String keyword = get_input("Please enter a keyword for your search:");
        String searchchoice = get_input("do you want to filter your search? (Y/N)");
        if (Objects.equals(searchchoice, "Y")){
            search_helper();
        }
        String sortchoice = get_input("do you want to sort by price or time posted? (price/time)");
        if (Objects.equals(sortchoice, "price")){
            String sortkey = get_input("do you want to sort by high to low or low to high? (high to low/low to high)");
        }
        else if (Objects.equals(sortchoice, "time")){
            String sortkey = get_input("do you want to sort by most recent or oldest? (recent/oldest)");
            //it feels like there should be a way to have these keys be easily replaced in case we want to
        }
        // call searcher here for the thing Searcher.search(searchkey, sortkey);
    }

    private static void search_helper(){
        System.out.println("yet to implement");
        //TODO: figure this out.
    }

    private static String get_input(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        return input.nextLine();
        //could edit this so that it checks whether the answer is acceptable and forces user to give another line if it's not?
    }
}