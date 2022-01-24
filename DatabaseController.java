// This class is to use the DatabaseControllers methods 
// If an encryption function is implemented, this would be where the databases would be encrypted 
public class DatabaseController extends DatabaseControllers {
    
    /**
     * Constructor Method for Controller of databases
     */
    public DatabaseController(){
        // initializing databases 
        CredentialsDatabase credentialsDatabase = super.credentialsDatabase;
        PatientDatabase patientDatabase = super.patientDatabase;
        RoomDatabase roomDatabase = super.roomDatabase;
    }
}
