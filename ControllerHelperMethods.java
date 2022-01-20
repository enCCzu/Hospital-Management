import java.io.*; 
import java.awt.*;
import java.util.ArrayList;
abstract class ControllerHelperMethods {
    
    String[] table = new String[];

    public boolean checkPassword(String userName, String password){
        
        return model.checkPassword(userName,password);
    }

    public String[] getTable(){
        table = model.getTable();
        return table;
    }

    public String[] sendTable(){

        return table;
    }

    public void editTable(int column, int id, String change){

        model.editPatient(column, id, change);
    }
}
