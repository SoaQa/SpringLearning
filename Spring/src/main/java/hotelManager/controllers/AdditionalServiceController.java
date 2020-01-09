package hotelManager.controllers;

import hotelManager.controllers.interfaces.IAdditionalServiceController;
import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.interfaces.IAdditionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import utils.ReflectionConstructor;
import java.util.List;

@Controller
@Slf4j
public class AdditionalServiceController implements IAdditionalServiceController {

    @Autowired
    private IAdditionalService additionalServices;


    public AdditionalService createAddService(String name, int cost, Room room, HotelClient client, int cDays){
        return additionalServices.createAService(name, cost, room, client,cDays);
    }

    public List<AdditionalService> getAdditionalServices(){
        return additionalServices.getAdditionalServices();
    }

    @Override
    public void addAdditionalService(AdditionalService add) {
        additionalServices.addAdditionalService(add);
    }

}
