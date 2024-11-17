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

    public void cart(){}

    public void historyoforders(){}


}
