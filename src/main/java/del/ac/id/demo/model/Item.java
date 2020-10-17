package del.ac.id.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("items")
public class Item {
    @Id
    String id;
    private String item_name,color_accept_installment_payment;
    private double stock,price,discount,rating;
    private int sold,seen,length;

    @DBRef
    @Field("item_detail")
    private ItemDetail itemDetail;

    public Item() {
    }

    public Item( String item_name, String color_accept_installment_payment, double stock, double price, double discount, double rating, int sold, int seen, int length, ItemDetail itemDetail) {
        this.id = id;
        this.item_name = item_name;
        this.color_accept_installment_payment = color_accept_installment_payment;
        this.stock = stock;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.sold = sold;
        this.seen = seen;
        this.length = length;
        this.itemDetail = itemDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getColor_accept_installment_payment() {
        return color_accept_installment_payment;
    }

    public void setColor_accept_installment_payment(String color_accept_installment_payment) {
        this.color_accept_installment_payment = color_accept_installment_payment;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
