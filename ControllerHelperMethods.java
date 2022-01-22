import java.io.*;
import java.awt.*;
import java.util.ArrayList;

abstract class ControllerHelperMethods {

    protected ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    protected LoginCredentialsDatabase loginDatabase = new LoginCredentialsDatabase();
    protected PatientDatabase patientDatabase = new PatientDatabase();
    protected RoomManagementDatabase roomDatabase = new RoomManagementDatabase();

    public boolean checkPassword(String userName, String password) {

        return checkPassword(userName, password);
    }

    public ArrayList<ArrayList<String>> getTable(String requestedTable) {

        if (requestedTable.equals("patient")){

            table = patientDatabase.getTable();

        }
        else if (requestedTable.equals("room")){

            table = roomDatabase.getTable();

        }
        return table;
    }

    public ArrayList<ArrayList<String>> sendTable(ArrayList<ArrayList<String>> viewTable) {

        table = viewTable;
        return table;
    }
}
