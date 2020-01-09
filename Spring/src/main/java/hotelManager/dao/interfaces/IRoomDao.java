package hotelManager.dao.interfaces;

import hotelManager.models.Room;
import utils.RoomStatus;

import java.util.List;

public interface IRoomDao extends IGenericDao<Room, Long> {
    List<Room> getFreeOrBusyRooms(RoomStatus param);
    List<Room> getRoomsWillBeFreeAfter(int cDays);
}
