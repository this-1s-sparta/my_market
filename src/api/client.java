package api;

public class client extends person{
    String name;
    String lastname;
    public client(String username,int password,String name,String lastname)
{
    super(username,password);
    this.name = name;
    this.lastname = lastname;
}

    public String getLastname()
    {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void viewproduct(product p)
    {

    }

    int sum=0;//μετεπειτα αρχικοποιηση στην κλαση
    public static void addtobasket(String title,int quantity,double cost)
    {
        Write("basket.txt",1,0,title+" "+quantity+" "+cost);
        sum =sum+cost;

    }


}
