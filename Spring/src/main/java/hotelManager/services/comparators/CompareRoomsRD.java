package hotelManager.services.comparators;

import hotelManager.models.Room;

import java.util.Comparator;

public class CompareRoomsRD implements Comparator<Room> {
    public int compare(Room r1, Room r2) {
        if (r1.getRentDate().isBefore(r2.getRentDate())){
            return -1;
        }
        else if (r2.getRentDate().isBefore(r1.getRentDate())){
            return 1;
        }
        else {
            return 0;
        }
    }
}
