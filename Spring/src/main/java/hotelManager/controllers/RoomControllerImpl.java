package hotelManager.controllers;

import hotelManager.controllers.interfaces.IRoomController;
import hotelManager.models.AdditionalService;
import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.interfaces.IAdditionalService;
import hotelManager.services.interfaces.IRoomService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import utils.ReflectionConstructor;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Data
@Controller
public class RoomControllerImpl implements IRoomController {


    @Autowired
    private IRoomService roomService;

    @Autowired
    private IAdditionalService additionalServices;



    public void putIn(Room room, HotelClient client, int cDays){
        roomService.putIn(room, client, cDays);
    }

    public List<Room> getSortedRooms(Comparator<Room> comparator){
        List<Room> rooms = roomService.getRooms();
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getSortedFreeRooms(Comparator<Room> comparator){
        List<Room> rooms = roomService.getFreeRooms();
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getSortedBusyRooms(Comparator<Room> comparator){
        List<Room> rooms = roomService.getBusyRooms();
        rooms.sort(comparator);
        return rooms;
    }

    public List<Room> getFreeRooms(){
      return roomService.getFreeRooms();
    }

    public List<Room> getBusyRooms(){
        return roomService.getBusyRooms();
    }

    public List<Room> getRoomsWillBeFreeAfter(int cDays){
        return roomService.getRoomsWillBeFreeAfter(cDays);
    }

    public int getSumCost(Room room){
        return roomService.getSumCost(room);
    }

    public void addService(Room room, AdditionalService newService){
        roomService.addService(room, newService);
        //additionalServices.addAdditionalService(newService);
    }

    public void evictFrom(Room room){
        roomService.evictFrom(room);
    }

    @Override
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    public Room getRoomByIndex(Long index) {
        return roomService.getRoomByIndex(index);
    }

    @Override
    public void removeService(Room room, String serviceName) {
        try {
            roomService.removeService(room, serviceName);
        }
        catch (RuntimeException e){
            log.error("removeService, RE exception! "+e.getMessage(), e);
        }
    }

    @Override
    public List<ArchiveClient> getHistoricalClients(Room room) {
        return roomService.getHistoricalClients(room);
    }
}
