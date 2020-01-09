package hotelManager.controllers.interfaces;

import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;

import java.util.List;

public interface IAdditionalServiceController {
    AdditionalService createAddService(String name, int cost, Room room, HotelClient client, int cDays);
    List<AdditionalService> getAdditionalServices();
    void addAdditionalService(AdditionalService additionalService);

}
