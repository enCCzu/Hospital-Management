import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract class ModelHelperMethods {

    private BufferedReader readFile; 
    private String currentLine; 

    /**
     * Reads the csv file and turns it into an ArrayList
     * @param table table that contains all the information
     * @param file Name of file that is being read
     */
    protected void csvToArrayList(ArrayList<ArrayList<String>> table, String file){

        try {

            int counter = 0; 

            readFile = new BufferedReader(new FileReader(file));

            while ((currentLine = readFile.readLine()) != null){

                String[] tempArray = currentLine.split("|");

                table.add(new ArrayList<String>());
                counter++; 

                for (int i = 0; i < tempArray.length; i++){
                    table.get(counter).add(i, tempArray[i]);
                }

            }

        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * Reads the csv file and turns it into an HashMap
     * @param hashMap HashMap containing user login information
     * @param file Name of file that is being read
     */
    protected void csvToHashMap(HashMap<String, String> hashMap, String file){

        try {

            readFile = new BufferedReader(new FileReader(file));

            while ((currentLine = readFile.readLine()) != null){

                String[] tempArray = currentLine.split("|");

                hashMap.put(tempArray[0], tempArray[1]);
            }

        }
        catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * Converts HashMap to csv so that it can be saved to database
     * @param userLogin User login information being saved
     */
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

    /**
     * Saves the arrayList information to csv 
     * @param table ArrayList of the information of that table
     * @param tableName Table being saved to specific file
     */
    protected  void arrayListToCSV(ArrayList<ArrayList<String>> table, String tableName){
        String line = "";
        try{
            // Initializing the File
            FileWriter file = new FileWriter(tableName + ".csv");

            // Initialing BufferedWriter
            BufferedWriter writer = new BufferedWriter(file);

            // Looping through ArrayList 
            for(ArrayList<String> i : table) {
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

    /**
     * Adds a row to the ArrayList and table and saves to csv
     * @param table Table that is being added to
     * @param listName Name of the table being added to
     */
    protected void addRow(ArrayList<ArrayList<String>> table, String listName) {

        table.add(new ArrayList<String>());

        arrayListToCSV(table, listName);

    }

    /**
     * Remove a specific row of the table
     * @param table Table being removed from
     * @param row Row index that is being removed
     */
    protected void removeRow(ArrayList<ArrayList<String>> table, int row){

        table.remove(row);

    }

    /**
     * Edits the table and updates information
     * @param table Table being edited
     * @param row Specific row being edited
     * @param column Specific column being edited
     * @param change Information that has been changed
     */
    protected void editTable(ArrayList<ArrayList<String>> table, int row, int column, String change){


        table.get(row).set(column, change);
        // get row and column from table 

    }

}
