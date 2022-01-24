import java.io.*;
import java.util.ArrayList;

public class RoomManagementDatabase extends ModelHelperMethods {

    private final String ROOM_LIST = "Room_List.csv";
    private ArrayList<ArrayList<String>> roomInfo = new ArrayList<ArrayList<String>>();

    /**
     * Gets the room table and sends it
     * @return ArrayList with room information
      */
    public ArrayList<ArrayList<String>> getTable(){

        return roomInfo; 

    }

    public RoomManagementDatabase(){

        csvToArrayList(roomInfo, ROOM_LIST);

    }

}