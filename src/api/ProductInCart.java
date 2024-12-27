package api;

public class ProductInCart {
    String name;
    int quantity;
    double price,priceOfOne;

    public ProductInCart(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price*quantity;
        this.priceOfOne = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }
    public double getPrice() {
        return price;

    }
    public void setPrice(double price) {
        this.price = price*quantity;
    }
    public double getPriceOfOne() {
        return priceOfOne;

    }
    public void setPriceOfOne(double price) {
        this.priceOfOne = price;
    }
}
