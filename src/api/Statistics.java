package api;
//MANAGER ONLY
public class Statistics {
    public static void Zero(String filename){
        int last=FileManagement.LastLine(filename);
        int line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0 τεμάχια");
        while (line!=-1 && line <= last){
            FileManagement.PrintLine(line-5,filename);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0 τεμάχια");
        }

        line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0kg");
        while (line!=-1 && line <= last){
            FileManagement.PrintLine(line-5,filename);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0kg");
        }
    }
}
