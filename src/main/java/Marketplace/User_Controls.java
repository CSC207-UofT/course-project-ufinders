import java.util.Objects;
import java.util.Scanner;

public class User_controls {

    /**
     * Prompts the user to choose a section of the marketplace to enter 
     * and directs them there.
     *
     */
    public static void intro(){
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to buy, sell, or exit the marketplace? (Write 'buy,' 'sell,' or 'exit')");
        String segmentchoice = input.nextLine();
        if (Objects.equals(segmentchoice, "buy")){
            buying_info();
        }
        else if (Objects.equals(segmentchoice, "sell")){
            selling_info();
        }
        else if (Objects.equals(segmentchoice, "exit")){
            MainController.menu();
        }
        else {
            intro();
        }
    }

    /**
     * Gets information on what item the user is selling and passes to Sell_Buy.
     * 
     *
     */
    private static void selling_info(){
        String name = get_input("What is the name of the item you're selling?");
        String description = get_input("What is the description of the item you're selling?");
        String price = get_input("What is the price of the item you're selling?");
        String contact = get_input("What is your contact information?");
        String password = get_input("What password would you like to use to delete this post after the item is sold?");
        Double price1 = Double.parseDouble(price);
        new SellBuy().create_post(name, description, price1, contact, password);
        System.out.println("Your post has been created!");

    }

    /**
     * Gets information on what item the user is searching for.
     *
     */
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

    /**
     * Gets information on what filters the searcher wants. 
     *
     */
    private static void search_helper(){
        System.out.println("yet to implement");
        //TODO: figure this out.
    }

    /**
     * Prints a prompt to the terminal and returns what the user writes in response. 
     *
     * @param prompt prompt to be printed for user
     */
    private static String get_input(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.println(prompt);
        String response =  input.nextLine();
      //  if (possibleinput.contains(response)){
      //      return response;
     //   }
      //  else{
      //      get_input(prompt, possibleinput);
      //  }
        return response;
        //could edit this so that it checks whether the answer is acceptable and forces user to give another line if it's not?
    }
}
