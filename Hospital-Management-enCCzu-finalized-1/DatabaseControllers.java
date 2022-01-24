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

    // Stores which user is logged in 
    protected String loggedInUsername = "";

    /**
     * Called by the view
     * Checks the username and password entered
     * @param userName Username entered
     * @param password Password entered
     * @return boolean true if matches system
     */
    public boolean checkPassword(String username, String password) {

        //Checks if the password matches the username
        if (credentialsDatabase.checkPassword(username, password)){

            //Returns true and saves username if it does
            loggedInUsername = username; 

            return true;
        }
        // Returns false if it does not match
        return false;
    }

    /**
     * Called by the view
     * Gets and saves the patient table
     * @return ArrayList of patient information
     */
    public ArrayList<ArrayList<String>> getPatientTable() {

        table = patientDatabase.getTable();

        return table;
    }


    /**
     * Called by the view
     * Gets and saves the room table
     * @return ArrayList of room information
     */
    public ArrayList<ArrayList<String>> getRoomTable() {

        table = roomDatabase.getTable();

        return table;
    }



    /**
     * Called by the view
     * Gets the number of patients 
     * Used by dashboard
     * @return int of number of patients
     */
    protected int getNumberOfPatients(){

        return patientDatabase.getNumberOfPatients();

    }

    /**
     * Called by the view
     * Gets the number of rooms
     * Used by dashboard
     * @return int of number of rooms
     */
    protected int getNumberOfRooms(){

        return roomDatabase.getNumberOfRooms();
        
    }

    /**
     * Called by the view
     * Gets the total number of beds
     * Used by dashboard
     * @return int of number of beds
     */
    protected int getTotalBeds(){

        return roomDatabase.getTotalBeds();
    }

    /**
     * Called by the view
     * Gets the number of available beds
     * Used by dashboard
     * @return int of available beds
     */
    protected int getAvailableBeds(){

        return roomDatabase.getAvailableBeds();
    }

    /**
     * Called by the view
     * Gets the username of the user
     * Used by Sidebar 
     * @return String of username
     */
    protected String getUsername(){

        return loggedInUsername;
    }

    /**
     * Called by view
     * Turns an ArrayList to an Array with same size
     * Array to be used in table
     * @param arrayList ArrayList to be converted
     * @return Array to be used in the table
     */
    public String[][] arrayListToArray(ArrayList<ArrayList<String>> arrayList){
        // Initialize the Array
        String[][] array = new String[arrayList.size()][];
        
        //Convert ArrayList to Array
        array = arrayList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        System.out.println(array.length);
        return array;
    }

    /**
     * Called by view 
     * Calls model to save the patient data to csv files
     * @param arrayList Information to be saved
     */
    public void savePatientData(ArrayList<ArrayList<String>> arrayList){

        patientDatabase.saveData(arrayList);

    }

}
