package hotelManager.services.comparators;

import hotelManager.models.Room;

import java.util.Comparator;

public class CompareRoomsByOccupancy implements Comparator<Room> {
    private String param;
    public CompareRoomsByOccupancy(String param){
        this.param = param;
    }

    public int compare(Room o1, Room o2) {
        if (this.param.equals("ASC")){
            return o1.getMaximumOccupancy() - o2.getMaximumOccupancy();
        }
        else if (this.param.equals("DESC")){
            return o2.getMaximumOccupancy() - o1.getMaximumOccupancy();
        }
        else {
            return o1.getMaximumOccupancy() - o2.getMaximumOccupancy();
        }
    }
}
