import java.io.*; 
import java.awt.*;
import java.util.ArrayList;
abstract class ControllerHelperMethods {
    
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>;

    public boolean checkPassword(String userName, String password){
        
        return model.userAuthentication(userName,password);
    }

    public ArrayList<ArrayList<String>> getTable(){
        table = model.getTable();
        return table;
    }

    public ArrayList<ArrayList<String>> sendTable(ArrayList<ArrayList<String>> viewTable){
        
        table = viewTable; 
        return table;
    }

    public void editTable(int column, int id, String change){

        model.editPatient(column, id, change);
    }
    protected int findNumberOfPatients(){
        int num = patientInfo.size();

        return num;
    }
}
