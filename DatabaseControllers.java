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
    protected LoginsDatabase loginDatabase = new LoginsDatabase(); 

    // Stores which user is logged in 
    protected String loggedInUsername = "";

    /**
     * Called by view
     * Checks the model
     * Checks the username and password entered
     * @param userName Username entered
     * @param password Password entered
     * @return boolean true if matches system
     */
    public boolean checkPassword(String username, String password) {

        return credentialsDatabase.checkPassword(username, password);
    }

    /**
     * Called by view to get table from model
     * Gets and saves the patient table
     * @return ArrayList of patient information
     */
    public ArrayList<ArrayList<String>> getPatientTable() {

        table = patientDatabase.getTable();

        return table;
    }


    /**
     * Called by view to get table from model
     * Gets and saves the room table
     * @return ArrayList of room information
     */
    public ArrayList<ArrayList<String>> getRoomTable() {

        table = roomDatabase.getTable();

        return table;
    }



    /**
     * Called by dashboard
     * Gets the number of patients 
     * @return int of number of patients
     */
    protected int getNumberOfPatients(){

        return patientDatabase.getNumberOfPatients();

    }

    /**
     * Called by dashboard
     * Gets the number of rooms
     * @return int of number of rooms
     */
    protected int getNumberOfRooms(){

        return roomDatabase.getNumberOfRooms();
        
    }

    /**
     * Called by dashboard
     * Gets the total number of beds
     * @return int of number of beds
     */
    protected int getTotalBeds(){

        return roomDatabase.getTotalBeds();
    }

    /**
     * Called by dashboard
     * Gets the number of available beds
     * @return int of available beds
     */
    protected int getAvailableBeds(){

        return roomDatabase.getAvailableBeds();
    }

    /**
     * Called by sidebar
     * Gets the username of the user
     * @return String of username
     */
    protected String getUsername(){

        return loginDatabase.getRecentLogin();
    }

    /**
     * Converts a 2D ArrayList to an 2DArray 
     * 2DArray to be used as a table
     * @param arrayList ArrayList to be converted
     * @return 2DArray of information
     */
    public String[][] arrayListToArray(ArrayList<ArrayList<String>> arrayList){
        String[][] array = new String[arrayList.size()][];
        array = arrayList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return array;
    }

    /**
     * Called by view to save the patient information
     * Calls model method to save to csv
     * @param arrayList ArrayList to be saved to csv
     */
    public void savePatientData(ArrayList<ArrayList<String>> arrayList){

        patientDatabase.saveData(arrayList);

    }
    
    /**
     * Stores the recent login information
     * @param username Username of the user
     * @param date Date of the login
     */
    protected void storeRecentLogin(String username, String date){

        loginDatabase.saveRecentLogin(username, date);

    }

}
