import java.util.ArrayList;
import java.util.HashMap;


public class Sell_Buy {

    /**
     * Creates the items to be sold
     * @param post a post with information given by user
     *
     */
    public HashMap<String, Object> post;

    public Sell_Buy(){
        this.post = new HashMap<>();
    }

//    Should we make the price an integer?
    public void create_post(String name, String description, Double price, String contact,
                                   String password, String email){

        /**
         * Stores the post into Database and creates the Item
         */

        this.post.put("name",name);
        this.post.put("description", description);
        this.post.put("password", password);
        this.post.put("price",price);
        this.post.put("contact",contact);
        this.post.put("email", email);
        Database.AddPost(post);
        Item item = new Item(name, description, contact, email, password, price);
        Database.StoreItem(item);
    }

//    We will need a unique id to differentiate the repeated titles or we will have to ask
//    users to create unique titles
    public boolean buy_items(String title){
        /**
         * Remove the item sold from post and return true
         */
        ArrayList<HashMap<String, Object>> posts = Database.GetLst();
        for (HashMap<String, Object> item: posts){
            if (item.get("name").equals(title)){
                posts.remove(item);
                return true;
            }
        }
        return false;
    }

}
