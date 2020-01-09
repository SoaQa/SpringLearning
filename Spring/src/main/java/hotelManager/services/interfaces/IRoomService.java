package hotelManager.services.interfaces;

import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.models.AdditionalService;

import java.util.List;

public interface IRoomService {
    void putIn(Room room, HotelClient client, int cDays);
    void evictFrom(Room room);
    Room changeCost(Room room, int newCost);
    Room startRestore(Room room);
    Room stopRestore(Room room);
    void addService(Room room, AdditionalService newService);
    void removeService(Room room, String serviceName);
    List<Room> getFreeRooms();
    List<Room> getBusyRooms();
    int getSumCost(Room room);
    List<Room> getRoomsWillBeFreeAfter(int cDays);
    List<Room> getRooms();
    Room getRoomByIndex(Long index);
    List<ArchiveClient> getHistoricalClients(Room room);
}
