
// imports 
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// Abstract base class for databases 

abstract class Databases {

    // Declaring variables or objects used in file reading or writing
    private BufferedReader readFile;
    private BufferedWriter writeFile;
    private String currentLine;

    /**
     * Reads csv file and creates a 2D String ArrayList to store the data
     * 
     * @param table is the ArrayList which the data is to be stored in
     * @param file  is the name of the csv file to be read
     */
    protected void csvToArrayList(ArrayList<ArrayList<String>> table, String file) {

        try {
            File checkFile = new File(file);
            // Checks if file exists
            if (!checkFile.exists() || checkFile.isDirectory()) {

                //If the file does not exist then it creates one 
                System.out.println("Cannot find required file. Creating new database file.");

                writeFile = new BufferedWriter(new FileWriter(file));

                writeFile.write("");

                writeFile.close();

            }

            // Index of the current ArrayList
            int counter = 0;

            // File Reader
            readFile = new BufferedReader(new FileReader(file));

            // Reads file and saves to ArrayList
            while ((currentLine = readFile.readLine()) != null) {

                String[] tempArray = currentLine.split("\\|\\!\\|");

                table.add(new ArrayList<String>());

                for (int i = 0; i < tempArray.length; i++) {
                    table.get(counter).add(tempArray[i]);
                }

                counter++;
            }

            readFile.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * Reads csv file and stores data into a HashMap
     * 
     * @param hashMap is the hashmap that the data is stored in
     * @param file    is the name of the file
     */
    protected void csvToHashMap(HashMap<String, String> hashMap, String file) {

        try {

            File checkFile = new File(file);

            //Checks if file exists
            if (!checkFile.exists() || checkFile.isDirectory()) {

                //Creates file if it does not exist
                System.out.println("Cannot find required file. Creating new database file.");

                writeFile = new BufferedWriter(new FileWriter(file));

                writeFile.write("");

                writeFile.close();

            }

            // File Reader
            readFile = new BufferedReader(new FileReader(file));

            // Saves each line to the HashMap
            while ((currentLine = readFile.readLine()) != null) {

                String[] tempArray = currentLine.split("\\|\\!\\|");

                if (tempArray.length == 2) {
                    hashMap.put(tempArray[0], tempArray[1]);
                } else {
                    hashMap.put(tempArray[0], null);
                }
            }
            readFile.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * Loops through HashMap to write csv file
     * 
     * @param hashMap is the HashMap that stores the data
     * @param file    is the name of the csv file to be written
     */
    protected void hashMapToCSV(HashMap<String, String> hashMap, String file) {

        try {

            // Initialing BufferedWriter
            writeFile = new BufferedWriter(new FileWriter(file));

            // Looping through Hashmap and writing to file
            for (String i : hashMap.keySet()) {
                if (hashMap.get(i) != null) {
                    writeFile.write(i + "|!|" + hashMap.get(i));
                } else {
                    writeFile.write(i);
                }
                writeFile.newLine();
            }

            // Close file writer
            writeFile.close();
        } catch (IOException except) {
            except.printStackTrace();
        }

    }

    /**
     * Loops through ArrayList to write csv file
     * 
     * @param table is the ArrayList that stores the data
     * @param file  is the name of the csv file that needs to be written
     */
    protected void arrayListToCSV(ArrayList<ArrayList<String>> table, String file) {
        try {
            // Current line being written
            String line = "";

            // Initialing BufferedWriter
            writeFile = new BufferedWriter(new FileWriter(file));

            // Looping through ArrayList
            for (ArrayList<String> i : table) {
                // Looping through Array and setting the line
                for (String j : i) {
                    line = line + j + "|!|";
                }
                // Writes to the file
                if (line.length() >= 3){
                    writeFile.write(line.substring(0, line.length() - 3));
                }
                writeFile.newLine();

                // Resets the line
                line = "";
            }

            // Close file
            writeFile.close();
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    /**
     * Add row to ArrayList (for example: adding a patient = adding a row to the
     * ArrayList)
     * 
     * @param table    is the ArrayList that needs a new row
     * @param listName is the name of the file that pertains to the ArrayList's data
     */
    protected void addRow(ArrayList<ArrayList<String>> table, String listName) {

        table.add(new ArrayList<String>());

        arrayListToCSV(table, listName);

    }

    /**
     * Removes a selected row from an ArrayList
     * 
     * @param table    is the ArrayList that needs a row removed
     * @param row      is the index of the row
     * @param listName is the name of the file that pertains to the ArrayList's data
     */
    protected void removeRow(ArrayList<ArrayList<String>> table, int row, String listName) {

        table.remove(row);

        arrayListToCSV(table, listName);

    }

    /**
     * Edits an element of the ArrayList
     * 
     * @param table    is the ArrayList that needs to be edited
     * @param row      is the index of the row of the element
     * @param column   is the index of the column of the element
     * @param change   is the String that the element should be changed to
     * @param listName is the name of the file that pertains to the ArrayList's data
     */
    protected void editTable(ArrayList<ArrayList<String>> table, int row, int column, String change, String listName) {

        table.get(row).set(column, change);
        // get row and column from table

        arrayListToCSV(table, listName);

    }

}