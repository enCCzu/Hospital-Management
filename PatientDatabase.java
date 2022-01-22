import java.io.*;
import java.util.ArrayList;

public class PatientDatabase extends ModelHelperMethods {

    private ArrayList<ArrayList<String>> patientInfo = new ArrayList<ArrayList<String>>(); // IS NOT 2D ARRAY IN CASE USER WANTS TO ADD MORE COLUMNS

    private final String PATIENT_LIST = "Patient_List.csv";


    public ArrayList<ArrayList<String>> getTable(){

        return patientInfo; 

    }

    public PatientDatabase(){

        csvToArrayList(patientInfo, PATIENT_LIST);

    }
}
