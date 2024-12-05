package api;

//API FILE
//Add(...),Change(...) methods using InTheFile
//these are Manager only methods!
public class Products {
    String title,category,subcategory,description,price,quantity;

    public Products(String title, String category, String subcategory, String description, String price, String quantity) {
        this.title = title;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    //those 2 methods are used to add and change products from the products.txt file
    //to have the following format:
            //Τίτλος: Πορτοκάλια 1kg
            //Περιγραφή: Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.
            //Κατηγορία: Φρέσκα τρόφιμα
            //Υποκατηγορία: Φρούτα
            //Τιμή: 1,20€
            //Ποσότητα: 200kg
    public static void Add(Products product) {
        //LastLine from FileManagement is used to find where the product will be added
        int line=FileManagement.LastLine("products.txt");
        line++;
        InTheFile(product,line,false, "products.txt");
    }

    //OldTitle must be in the form :"Τίτλος: Πορτοκάλια 1kg"
    public static void Change(String OldTitle, Products product) {
        int line=FileManagement.ThatLine("products.txt",OldTitle);
        //find the line of the title in the file and overwrite with the new content
        InTheFile(product,line,true, "products.txt");
    }

    //this method is called to make changes in the file in the wanted format
    //if overwrite is false it was called by Add, else if it is true it was called by Change
    //this is done to reuse the same code for both methods.
    public static void InTheFile(Products product,int line,boolean overwrite,String filename){
        //write from FileManagement is used to add the product on "products.txt"
        FileManagement.Write(filename, line, overwrite, "Τίτλος: "+product.title);
        FileManagement.Write(filename, line+1, overwrite, "Περιγραφή: "+product.description);
        FileManagement.Write(filename, line+2, overwrite, "Κατηγορία: "+product.category);
        FileManagement.Write(filename, line+3, overwrite, "Υποκατηγορία: "+product.subcategory);
        FileManagement.Write(filename , line+4, overwrite, "Τιμή: "+product.price);
        FileManagement.Write(filename, line+5, overwrite, "Ποσότητα: "+product.quantity);
    }
}
