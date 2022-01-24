// imports 
import java.util.HashMap;

public class CredentialsDatabase extends Databases {

    // Declaring and initializing variables/objects related to the User Credentials Database 
    private final String USER_LIST = "User_List.csv";
    private HashMap<String, String> loginInfo = new HashMap<String, String>();
    private String loggedInUsername = "";

    /**
     * Called by the Controller
     * Checks to see if the entered username and password matches with the database 
     * @param username is the entered username  
     * @param password is the entered password 
     * @return boolean (true or false) if the username matches the password
     */
    public boolean checkPassword(String username, String password){

        // checks if the user exists in the database 
        if (loginInfo.containsKey(username)){

            if (loginInfo.get(username) != null){

                if(loginInfo.get(username).equals(password)){// checks if the password of the username in the database matches with the entered password

                    loggedInUsername = username;
                    return true;
                }

            }
            else {
                // if the username's password is null, it means that the account doesn't require a password 

                return true;

            }
            
        }
        // if the username doesn't exist, it is not a registered user 
        return false; 
        
    } 

    /**
     * Calls Database method 'hashMapToCSV' to save current HashMap to a CSV file 
     */
    public void saveHashMap(){

        hashMapToCSV(loginInfo, USER_LIST);

    }
    
    /**
     * Called by the constructor 
     * Gets the username of the user 
     * @return String of the username
     */
    public String getUsername(){
        
        return loggedInUsername;
    }

    /**
     *  Constructor for this database class 
     */
    public CredentialsDatabase(){

        // Reads csv file to create HashMap
        csvToHashMap(loginInfo, USER_LIST);

    }



}
