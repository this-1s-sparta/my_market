package api;

public class manager extends person
{
public manager(String username, String password)
{
    super(username,password);
}

public void insertproduct(String title,String category,String subcategory, String description, double price, int quantity)
{
    product p;
    p=new product(title,category,subcategory,description,price,quantity);
}



}
