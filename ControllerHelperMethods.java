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
    protected int findNumberOfPatients(){
        return patientDatabase.getTable().size();
    }

    protected int findTotalBeds(){
        int totalBeds = 0;
        ArrayList<ArrayList<String>> roomInfo = roomDatabase.getTable();
        for(int i = 0; i < roomInfo.size(); i++){
            totalBeds += Integer.parseInt(roomInfo.get(i).get(1));
        }

        return totalBeds;
    }

    protected int findAvailableBeds(){
        int availableBeds = 0;
        ArrayList<ArrayList<String>> roomInfo = roomDatabase.getTable();
        for(int i = 0; i < roomInfo.size(); i++){
            availableBeds += Integer.parseInt(roomInfo.get(i).get(2));
        }

        return availableBeds;
    }

    protected int findNumberOfRooms(){
        return roomDatabase.getTable().size();
    }
}
