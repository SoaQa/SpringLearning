package hotelManager.controllers.interfaces;

import hotelManager.models.AdditionalService;
import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;

import java.util.Comparator;
import java.util.List;

public interface IRoomController {
    void putIn(Room room, HotelClient client, int cDays);
    List<Room> getSortedRooms(Comparator<Room> comparator);
    List<Room> getSortedFreeRooms(Comparator<Room> comparator);
    List<Room> getSortedBusyRooms(Comparator<Room> comparator);
    List<Room> getFreeRooms();
    List<Room> getBusyRooms();
    List<Room> getRoomsWillBeFreeAfter(int cDays);
    int getSumCost(Room room);
    void addService(Room room, AdditionalService newService);
    void evictFrom(Room room);
    List<Room> getRooms();
    Room getRoomByIndex(Long index);
    void removeService(Room room, String serviceName);
    List<ArchiveClient> getHistoricalClients(Room room);
}
