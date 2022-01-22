import java.io.*;
import java.util.HashMap;

public class LoginCredentialsDatabase extends ModelHelperMethods {

    private final String USER_LIST = "User_List.csv";
    private HashMap<String, String> loginInfo = new HashMap<>();

    public LoginCredentialsDatabase(){

        csvToHashMap(loginInfo, USER_LIST);
        
    }

}
