package api;

public class product {
    String title;
    String category,subcategory;
    String description;
    double price;
    int quantity;

    public product(String title,String category, String subcategory, String description, double price, int quantity) {
        this.title = title;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

}
