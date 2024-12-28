package api;

public class Person {
    String username;
    String password;
//constructor for objects of the class with username and password
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
//getter for the username
    public String getUsername() {
        return username;
    }
//getter for the password
    public String getPassword() {
        return password;
    }
//setter for the username
    public void setUsername(String username) {
        this.username = username;
    }
//setter for the password
    public void setPassword(String password) {
        this.password = password;
    }







}
