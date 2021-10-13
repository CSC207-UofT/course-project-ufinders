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
    public HashMap<String, Object> create_post(String name, String description, Float price, String contact,
                                                      String password){

        /**
         * Creates the post through User_Controls and returns
         * list of info of post
         */

        this.post.put("name",name);
        this.post.put("description", description);
        this.post.put("password", password);
        this.post.put("price",price);
        this.post.put("contact",contact);
        Database.AddPost(post);

        return post;
    }

//    We will need a unique id to differentiate the repeated titles or we will have to ask
//    users to create unique titles
    public boolean buy_items(String title){
        /**
         * Remove the item sold from post and return true
         */
        ArrayList<HashMap<String, Object>> posts = Database.item_lst;
        for (HashMap<String, Object> item: posts){
            if (item.get("name").equals(title)){
                posts.remove(item);
                return true;
            }
        }
        return false;
    }

}
