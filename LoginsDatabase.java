// imports 
import java.util.Date; 
import java.util.ArrayList;

// Database for storing logins (when users login to the program, it is saved to the database)
public class LoginsDatabase extends Databases {

    // Declaring and initializing variables/objects related to the logins database
    private final String LOGIN_LIST = "Databases/Logins_List.csv";
    private ArrayList<ArrayList<String>> loginInfo = new ArrayList<ArrayList<String>>(); 

    /**
     * Allows controller to send a table to the database to be stored 
     * @param table is the table data to be stored 
     */
    public void saveData(ArrayList<ArrayList<String>> table){

        loginInfo = table; 

        arrayListToCSV(loginInfo, LOGIN_LIST);

    }

    /**
     * Gets the recent login information
     * @return Username of recent login
     */
    public String getRecentLogin(){

        int tableSize = loginInfo.size();

        if (tableSize > 0){
            return loginInfo.get(tableSize - 1).get(0);
        }

        return null;

    }

    /**
     * Saves the most recent login information
     * @param username Username of user
     * @param date Date it was accessed
     */
    public void saveRecentLogin(String username, String date){

        ArrayList<String> recentLogin = new ArrayList<String>(); 
        recentLogin.add(username);
        recentLogin.add(date);

        loginInfo.add(recentLogin); 

        arrayListToCSV(loginInfo, LOGIN_LIST);

    }

    public LoginsDatabase() {

        csvToArrayList(loginInfo, LOGIN_LIST);

    }
    
}
