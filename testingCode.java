import java.util.ArrayList;
class testingCode{
    public static void main(String[] args){
        //ArrayList
        ArrayList<String[]> patientTable = new ArrayList<String[]>();
        String[] patient1 = {"Annie", "00004", "7", "1/1//22", "4", "Status", "Diagnosis", "Attending Staff"};
        String[] patient2 = {"Ashe", "00005", "23", "1/1//22", "5", "Status", "Diagnosis", "Attending Staff"};
        String[] patient3 = {"Caitlyn", "00006", "27", "1/1//22", "6", "Status", "Diagnosis", "Attending Staff"};
        String[] patient4 = {"Camille", "00002", "64", "1/1//22", "2", "Status", "Diagnosis", "Attending Staff"};
        String[] patient5 = {"Diana", "00008", "23", "1/1//22", "8", "Status", "Diagnosis", "Attending Staff"};
        patientTable.add(patient1);
        patientTable.add(patient2);
        patientTable.add(patient3);
        patientTable.add(patient4);
        patientTable.add(patient5);
        
        //Table Column Names
        String[] columnNames = {"Name", "Patient ID", "Age", "Check-in Date", "Room #", "Status", "Diagnosis", "Attending Staff"};
        
        //Two ways to turn to 2D array
        Object[][] patientTableArray = patientTable.toArray(new Object[patientTable.size()][]);

        // String[][] patientTableArray = new String[patientTable.size()][columnNames.length];

        // for(int i = 0; i < patientTable.size(); i++){
        //     patientTableArray[i] = patientTable.get(i);
        // }
        System.out.println("done");
    }
}
