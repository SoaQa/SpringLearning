package hotelManager.services.interfaces;

import hotelManager.models.HotelClient;
import java.util.Comparator;
import java.util.List;


public interface IClientService {
    HotelClient createHotelClient(String name, String surname, int age, long roomID, int cDays);
    List<HotelClient> getSortedClients(Comparator comparator);
    List<HotelClient> getHotelClients();
}
