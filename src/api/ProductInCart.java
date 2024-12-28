package api;

public class ProductInCart {
    String name;
    int quantity;
    double price,priceOfOne;

    public ProductInCart(String name, int quantity, double price) {//constructor for objects of the class
        this.name = name;
        this.quantity = quantity;
        this.price = price*quantity;
        this.priceOfOne = price;
    }
    public String getName() {
        return name;
    }//returns name
    public void setName(String name) {//sets name
        this.name = name;

    }
    public int getQuantity() {
        return quantity;
    }//return quality
    public void setQuantity(int quantity) {//sets quantity
        this.quantity = quantity;

    }
    public double getPrice() {//returns price
        return price;

    }
    public void setPrice(double price) {//sets price
        this.price = price*quantity;
    }
    public double getPriceOfOne() {//returns price of one
        return priceOfOne;

    }
    public void setPriceOfOne(double price) {//sets price of one
        this.priceOfOne = price;
    }
}
