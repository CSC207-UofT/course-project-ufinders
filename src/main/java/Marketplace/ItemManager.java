import java.util.ArrayList;
import java.util.HashMap;


public class ItemManager {

    /**
     * Creates the items to be sold
     * @param post a post with information given by user
     *
     */
    public HashMap<String, Object> post;

    public ItemManager(){
        this.post = new HashMap<>();
    }

    public void create_post(String name, String description, Double price, String contact,
                                   String password, String email){

        /**
         * Stores the post into Database and creates the Item. Got the item from User_Controls
         */

        this.post.put("name",name);
        this.post.put("description", description);
        this.post.put("password", password);
        this.post.put("price",price);
        this.post.put("contact",contact);
        this.post.put("email", email);
        Item item = new Item(name, description, contact, email, password, price);
        Database.StoreItem(item);
    }

//    We will need a unique id to differentiate the repeated titles or we will have to ask
//    users to create unique titles
    public boolean buy_items(String title){
        /**
         * Removes the item sold from post and return true
         */
        ArrayList<Item> posts = Database.GetLst();

        for (Item item: posts){
            if (item.getName().equals(title)){
                posts.remove(item);
                return true;
            }
        }
        return false;
    }



}
