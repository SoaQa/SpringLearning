package hotelManager.services.comparators;

import hotelManager.models.Room;

import java.util.Comparator;

public class CompareRoomsByCost implements Comparator<Room> {
    private String param;
    public CompareRoomsByCost(String param){
        this.param = param;
    }

    public int compare(Room o1, Room o2) {
        if (this.param.equals("ASC")){
            return o1.getCostPerDay() - o2.getCostPerDay();
        }
        else if (this.param.equals("DESC")){
            return o2.getCostPerDay() - o1.getCostPerDay();
        }
        else {
            return o1.getCostPerDay() - o2.getCostPerDay();
        }
    }
}
