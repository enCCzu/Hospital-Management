//imports 
import java.io.*;
import java.awt.*;
import java.util.ArrayList;

abstract class DatabaseControllers {
    
    // Table that will be sent to view 
    protected ArrayList<ArrayList<String>> table;

    // Initializing Model databases 
    protected CredentialsDatabase credentialsDatabase = new CredentialsDatabase();
    protected PatientDatabase patientDatabase = new PatientDatabase();
    protected RoomDatabase roomDatabase = new RoomDatabase();

    /**
     * 
     * @param userName
     * @param password
     * @return
     */
    public boolean checkPassword(String userName, String password) {

        return credentialsDatabase.checkPassword(userName, password);
    }

    /**
     * 
     * @return
     */
    public ArrayList<ArrayList<String>> getPatientTable() {

        table = patientDatabase.getTable();

        return table;
    }


    /**
     * 
     * @return
     */
    public ArrayList<ArrayList<String>> getRoomTable() {

        table = roomDatabase.getTable();

        return table;
    }



    /**
     * 
     * @return
     */
    protected int getNumberOfPatients(){

        return patientDatabase.getNumberOfPatients();

    }

    /**
     * 
     * @return
     */
    protected int getNumberOfRooms(){

        return roomDatabase.getNumberOfRooms();
        
    }

    /**
     * 
     * @return
     */
    protected int getTotalBeds(){

        return roomDatabase.getTotalBeds();
    }

    /**
     * 
     * @return
     */
    protected int getAvailableBeds(){

        return roomDatabase.getAvailableBeds();
    }

}
