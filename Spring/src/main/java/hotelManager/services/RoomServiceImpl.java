package hotelManager.services;

import hotelManager.dao.interfaces.IClientDao;
import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.models.AdditionalService;
import hotelManager.services.interfaces.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import utils.RoomStatus;

import java.time.*;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@PropertySource("classpath:application.yml")
public class RoomServiceImpl implements IRoomService {

    @Value("${modifyRoomStatus}")
    private boolean modifyRoomStatus;

    @Value("${cntHistorycalClients}")
    private int cntHistorycalClients;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private IClientDao clientDao;

    public void putIn(Room room, HotelClient client, int cDays) {
        try{
        if (cDays > 0 || modifyRoomStatus) {
            Date now = new Date();
            LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate rentDate = localDate.plusDays(cDays);
            room.setRentDate(rentDate);
            room.setClient(client);
            room.setFree(false);
            client.setRentDate(rentDate);
            }
        else{
            log.error("Не выполнены обязательные условия!");
            throw new RuntimeException("Не выполнены обязательные условия!");
        }
        }
        catch (NullPointerException e){
            log.error("putIn NPE", e);
        }
        catch (RuntimeException e){
            log.error(e.getMessage(), e);
        }
        catch (Exception e){
            log.error("putIn unknown error", e);
        }
    }


    public void evictFrom(Room room) {
        try{
            if(modifyRoomStatus){
                Date now = new Date();
                LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                room.setFree(true);
                room.setRentDate(localDate);
                List<HotelClient> histClients = room.getHistoryClients();
                if (histClients.size() < cntHistorycalClients){
                    histClients.add(room.getClient());
                }
                else if (histClients.size() == cntHistorycalClients){
                    histClients.remove(0);
                    histClients.add(room.getClient());
                }

                while(histClients.size() > cntHistorycalClients){
                    histClients.remove(0);
                }

                room.setHistoryClients(histClients);
                room.setClient(null);
                room.setAdditionalServices(null);
            }
            else {
                log.error("Не выполнены обязательные условия!");
                throw new RuntimeException("Не выполнены обязательные условия!");
            }
        }
        catch (NullPointerException e){
            log.error("evictFrom NPE error", e);
        }
        catch (RuntimeException e){
            log.error(e.getMessage(), e);
        }
        catch (Exception e){
            log.error("evictFrom unknown error", e);
        }
    }

    public Room changeCost(Room room, int newCost) {
        if (newCost > 300){
            room.setCostPerDay(newCost);
        }
        return room;
    }

    public Room startRestore(Room room) {
        if (modifyRoomStatus){
        room.setRestore(true);
        return room;
        }
        else {
            log.error("Операция запрещена!");
            throw new RuntimeException("Операция запрещена!");
        }
    }

    public Room stopRestore(Room room) {
        if (modifyRoomStatus){
        room.setRestore(false);
        return room;
        }
        else {
            log.error("Операция запрещена!");
            throw new RuntimeException("Операция запрещена!");
        }
    }


    public void addService(Room room, AdditionalService newService){
        try {
            List<AdditionalService> additionalServices = room.getAdditionalServices();
            additionalServices.add(newService);
            room.setAdditionalServices(additionalServices);
        }
        catch (NullPointerException e){
            log.error("addService", e);
        }

    }

    public void removeService(Room room, String serviceName) throws RuntimeException {
        List<AdditionalService> additionalServices = room.getAdditionalServices();
        int confirm = 0;
        for (int i = 0; i < additionalServices.size(); i++){
            if (additionalServices.get(i).getServiceName().equals(serviceName)){
                additionalServices.remove(i);
                confirm = 1;
                break;
            }
        }
        if (confirm == 0){
            throw new RuntimeException("Service for remove, not found!");
        }
    }

    public List<Room> getFreeRooms(){
        return roomDao.getFreeOrBusyRooms(RoomStatus.FREE);
    }

    public List<Room> getBusyRooms(){
        return roomDao.getFreeOrBusyRooms(RoomStatus.BUSY);
    }


    public int getSumCost(Room room){
        int servicesCost=0;
        for (int i = 0; i < room.getAdditionalServices().size(); i++){
            servicesCost+=room.getAdditionalServices().get(i).getServiceCost();
        }
        return room.getCostPerDay() + servicesCost;
    }


    public List<Room> getRoomsWillBeFreeAfter(int cDays){
        /*Date now = new Date();
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.plusDays(cDays);
        LocalDate finalLocalDate = localDate;
        return getBusyRooms().stream().filter(room -> finalLocalDate.isAfter(room.getRentDate())).collect(Collectors.toList());
         */
        return roomDao.getRoomsWillBeFreeAfter(cDays);
    }



    public List<Room> getRooms() {
        return roomDao.getAllData();
    }


    public Room getRoomByIndex(Long id) {
        try {
            return roomDao.getByID(id);
        }
        catch (IndexOutOfBoundsException e){
            log.error("getRoomByIndex out of index", e);
            return null;
        }
        catch (Exception e){
            log.error("getRoomByIndex unknown exception", e);
            return null;
        }
    }

    @Override
    public List<ArchiveClient> getHistoricalClients(Room room) {
        System.out.println(cntHistorycalClients);
        System.out.println(modifyRoomStatus);
        return clientDao.getHistorycalClients(room, cntHistorycalClients);
    }


}
