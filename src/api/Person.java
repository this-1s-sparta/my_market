package api;

public class Person {
    String username;
    String password;
//constructor με παραμέτρους το username και το password
    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }
//getter για το username
    public String getUsername() {
        return username;
    }
//getter για το password
    public String getPassword() {
        return password;
    }
//setter για το username
    public void setUsername(String username) {
        this.username = username;
    }
//setter για το password
    public void setPassword(String password) {
        this.password = password;
    }







}
