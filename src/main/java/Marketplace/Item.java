//package Marketplace;
//
//import java.io.Serializable;
//
//public class Item implements Serializable {
//    /**
//     * An item class that stores the users' information and item's attributes
//     *
//     * @param name the item's name
//     * @param item_description the description of the item
//     * @param contact_num the seller's phone number
//     * @param contact_email the seller's email
//     * @param price the item's price
//     * @param password the item's deletion password
//     * @param campus the seller's campus
//     * @param condition the item's condition
//     * @param picture the item's picture
//     */
//
//    private final String name;
//    private final String item_description;
//    private final String contact_num;
//    private final String contact_email;
//    private final String password;
//    private final double price;
//
//    /* TODO: add picture attribute, implement campus and condition enums
//     */
////    private enum campus{
////        UTSG, UTM, UTSC
////    }
////    private campus campus;
////    private enum condition{
////        Used, New, LikeNew
////    }
////    private condition condition;
//
//
//    public Item(String title, String item_description, String contact_num, String contact_email, String password,
//                double price){
//        this.name = title;
//        this.item_description = item_description;
//        this.contact_num = contact_num;
//        this.contact_email = contact_email;
//        this.password = password;
//        this.price = price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getItem_description() {
//        return item_description;
//    }
//
//    public String getContact_num() {
//        return contact_num;
//    }
//
//    public String getContact_email() {
//        return contact_email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//}
