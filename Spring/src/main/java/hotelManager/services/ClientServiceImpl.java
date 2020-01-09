package hotelManager.services;

import hotelManager.dao.interfaces.IClientDao;
import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.HotelClient;
import hotelManager.services.interfaces.IClientService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Slf4j
@Data
@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Autowired
    private IRoomDao roomDao;


    public HotelClient createHotelClient(String name, String surname, int age, long roomID, int cDays) {
        HotelClient hotelClient = new HotelClient();
        hotelClient.setAge(age);
        hotelClient.setFirstName(name);
        hotelClient.setSurname(surname);
        hotelClient.setRoom(roomDao.getByID(roomID));
        hotelClient.setRentDate(LocalDate.now().plusDays(cDays));
        clientDao.merge(hotelClient);
        return hotelClient;
    }

    public List<HotelClient> getSortedClients(Comparator comparator){
        List<HotelClient> hotelClients = clientDao.getAllData();
        hotelClients.sort(comparator);
        return hotelClients;
    }

    public List<HotelClient> getHotelClients() {
        return clientDao.getAllData();
    }
}
