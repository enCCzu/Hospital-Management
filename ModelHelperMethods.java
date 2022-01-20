import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract class ModelHelperMethods {

    private final String PATIENT_LIST = "Patient_List.csv";
    private BufferedReader readFile; 
    private String currentLine; 
    private ArrayList<ArrayList<String>> patientInfo = new ArrayList<ArrayList<String>>(); // IS NOT 2D ARRAY IN CASE USER WANTS TO ADD MORE COLUMNS
    private final String USER_LIST = "User_List.csv";
    private HashMap<String, String> loginInfo = new HashMap<>();


    protected void csvToArrayList(){

        try {

            int counter = 0; 

            readFile = new BufferedReader(new FileReader(PATIENT_LIST));

            while ((currentLine = readFile.readLine()) != null){

                String[] tempArray = currentLine.split("|");

                patientInfo.add(new ArrayList<String>());
                counter++; 

                for (int i = 0; i < tempArray.length; i++){
                    patientInfo.get(counter).add(i, tempArray[i]);
                }

            }

        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }

    //https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/#:~:text=HashMap%20is,type%20(e.g.%20an%20Integer).
    protected void csvToHashMap(){

        try {

            readFile = new BufferedReader(new FileReader(USER_LIST));

            while ((currentLine = readFile.readLine()) != null){

                String[] tempArray = currentLine.split("|");

                loginInfo.put(tempArray[0], tempArray[1]);
            }

        }
        catch (IOException e) {

            e.printStackTrace();
        }

    }

    protected void hashMapToCSV(HashMap<String,String> userLogin){
        try{
            // Initializing the File
            FileWriter file = new FileWriter("UserLogin.csv");

            // Initialing BufferedWriter
            BufferedWriter writer = new BufferedWriter(file);

            // Looping through Hashmap and writing to file
            for(String i : userLogin.keySet()) {
                writer.write(i + "|" + userLogin.get(i));
                writer.newLine();
            }

            // Close file
            writer.close();

            System.out.println("Written successfully");
        }
        catch (IOException except)
        {
            except.printStackTrace();
        }

    }


    protected  void arrayListToCSV(ArrayList<String[]> table, String tableName){
        String line = "";
        try{
            // Initializing the File
            FileWriter file = new FileWriter(tableName + "Table.csv");

            // Initialing BufferedWriter
            BufferedWriter writer = new BufferedWriter(file);

            // Looping through ArrayList 
            for(String[] i : table) {
                //Looping through Array and setting the line
                for(String j : i){
                    line = line + j + "|";
                }
                //Writes to the file
                writer.write(line.substring(0, line.length()-1));
                writer.newLine();

                //Resets the line
                line = "";
            }

            // Close file
            writer.close();

            System.out.println("Written successfully");
        }
        catch (IOException except)
        {
            except.printStackTrace();
        }
    }

    protected ArrayList<ArrayList<String>> getTable(){

        return patientInfo; 

    }

    protected void addRow() {

        patientInfo.add(new ArrayList<String>());

    }

    protected void removeRow(int row){

        patientInfo.remove(row);

    }

    protected void editPatient(int column, String id, String change){

        for (int i = 0; i < patientInfo.size(); i++){


            if (patientInfo.get(i).get(1).equals(id)){

                patientInfo.get(i).set(column, change);


            }
        }

    }

}