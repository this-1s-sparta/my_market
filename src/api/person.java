package api;
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
//αναζλητηση προιοντος μονο με τιτλο, διδικασια την οποια μπορουν να πραγματοποιησουν τοσο οι διαχειριστεσς οσο και οι πελατες
    public ArrayList searchforproduct(String title)
    {

    }
    //αναζλητηση προιοντος με τιτλο και κατηγορια, διδικασια την οποια μπορουν να πραγματοποιησουν τοσο οι διαχειριστεσς οσο και οι πελατες

    public ArrayList searchforproduct(String title,String category)
    {

    }
//    /αναζλητηση προιοντος  με τιτλο,κατηγορια και υποκατηγορια, διδικασια την οποια μπορουν να πραγματοποιησουν τοσο οι διαχειριστεσς οσο και οι πελατες
    public ArrayList searchforproduct(String title,String category,String subcategory)
    {

    }
    //αναζλητηση προιοντος μονο με κατηγορια, διδικασια την οποια μπορουν να πραγματοποιησουν τοσο οι διαχειριστεσς οσο και οι πελατες

    public ArrayList searchforproduct(String category)
    {

    }





}
