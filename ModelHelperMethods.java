import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract class ModelHelperMethods {

    protected final String PATIENT_LIST = "Patient_List.csv";
    


    protected void csvToArrayList(){


    }

    protected void csvToHashMap(){



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

    protected ArrayList getTable(){

        return ArrayList; 

    }

    protected void addOrRemoveRow() {


    }

    protected void editTable(){

        
    }
}