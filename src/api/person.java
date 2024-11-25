package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class person {
    String username;
    int password;
//constructor με παραμέτρους το username και το password
    public person(String username, int password) {
        this.username = username;
        this.password = password;
    }
//getter για το username
    public String getUsername() {
        return username;
    }
//getter για το password
    public int getPassword() {
        return password;
    }
//setter για το username
    public void setUsername(String username) {
        this.username = username;
    }
//setter για το password
    public void setPassword(int password) {
        this.password = password;
    }







}
