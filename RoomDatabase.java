//Imports 
import java.util.ArrayList;

// List of rooms and information pertaining to the rooms for room management 
public class RoomDatabase extends Databases {

    // Declaring and initializing variables/objects related to the room database  
    private final String ROOM_LIST = "Databases/Room_List.csv";
    // Used ArrayList of ArrayLists instead of ArrayList of Arrays so optional features, such as: add column, are possible
    private ArrayList<ArrayList<String>> roomInfo = new ArrayList<ArrayList<String>>();
    
    /**
     * Allows controller to access the ArrayList 
     * @return room ArrayList that stores the room database 
     */
    public ArrayList<ArrayList<String>> getTable(){

        return roomInfo; 

    }

    /**
     * Allows controller to send a table to the database to be stored 
     * @param table is the table data to be stored 
     */
    public void saveData(ArrayList<ArrayList<String>> table){

        roomInfo = table; 

        arrayListToCSV(roomInfo, ROOM_LIST);

    }


    /**
     * Is called by a controller. This method calls the Database method 'addRow' to add another row to the room ArrayList
     * Adding another row to the room ArrayList means that the user wants to add another room into the database 
     */
    public void addRow(){

        addRow(roomInfo, ROOM_LIST);

    }

    /**
     * Is called by a controller. Removes a selected row from the room ArrayList 
     * Calls Database method 'removeRow' which removes a selected row (each row is an ArrayList) 
     * Removing a row means the user wants to delete a room from the database 
     * @param row is the row to be deleted 
     */
    public void removeRow(int row){


        removeRow(roomInfo, row, ROOM_LIST);

    }

    /**
     * Is called by a controller. Edits a specific element from the room ArrayList 
     * Calls Database method 'editTable' which changes an element of the ArrayList 
     * @param row is the row/room that needs to be changed 
     * @param column is the column that needs to be changed 
     * @param change is the String that the element should change to
     */
    public void editTable(int row, int column, String change){


        editTable(roomInfo, row, column, change, ROOM_LIST);

    }

    /**
     * Loops through room ArrayList and adds up all of the beds in each room 
     * @return # of beds total 
     */
    protected int getTotalBeds(){
        int totalBeds = 0;
        for(int i = 0; i < roomInfo.size(); i++){
            totalBeds += Integer.parseInt(roomInfo.get(i).get(1));
        }

        return totalBeds;
    }

    /**
     * Loops through room ArrayList and adds up all of the available beds in each room 
     * @return total # of available beds 
     */
    protected int getAvailableBeds(){
        int availableBeds = 0;
        for(int i = 0; i < roomInfo.size(); i++){
            try {
                availableBeds += Integer.parseInt(roomInfo.get(i).get(2));
            }
            catch (NumberFormatException e){

                System.out.println("Please organize the data correctly.");
                System.out.println(e);

            }
        }

        return availableBeds;
    }

    /**
     * Returns the total number of rooms in the database 
     * @return # of rooms 
     */
    protected int getNumberOfRooms(){
        return roomInfo.size();
    }

    /**
     * Constructor for the room database 
     */
    public RoomDatabase(){

        // Reads csv file to make ArrayList/store information
        csvToArrayList(roomInfo, ROOM_LIST);

    }

}
