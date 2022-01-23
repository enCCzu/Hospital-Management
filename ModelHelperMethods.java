import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

abstract class ModelHelperMethods {

    private BufferedReader readFile; 
    private String currentLine; 

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

    // rewrite 
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

    protected void addRow(ArrayList<ArrayList<String>> table, String listName) {

        table.add(new ArrayList<String>());

        arrayListToCSV(table, listName);

    }

    protected void removeRow(ArrayList<ArrayList<String>> table, int row){

        table.remove(row);

    }

    protected void editTable(ArrayList<ArrayList<String>> table, int row, int column, String change){


        table.get(row).set(column, change);
        // get row and column from table 

    }

}
