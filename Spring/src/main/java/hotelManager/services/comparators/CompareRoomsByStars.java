package hotelManager.services.comparators;

import hotelManager.models.Room;

import java.util.Comparator;

public class CompareRoomsByStars implements Comparator<Room> {
    private String param;
    public CompareRoomsByStars(String param){
        this.param = param;
    }
    public int compare(Room o1, Room o2) {
        if (this.param.equals("ASC")){
            return o1.getStarsCount() - o2.getStarsCount();
        }
        else if (this.param.equals("DESC")){
            return o2.getStarsCount() - o1.getStarsCount();
        }
        else {
            return o1.getStarsCount() - o2.getStarsCount();
        }
    }
}
