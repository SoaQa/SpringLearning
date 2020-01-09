package hotelManager.services.interfaces;

import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;

import java.util.List;

public interface IAdditionalService {
    AdditionalService createAService(String name, int cost, Room room, HotelClient client, int cDays);
    void addAdditionalService(AdditionalService additionalService);
    List<AdditionalService> getAdditionalServices();
}
