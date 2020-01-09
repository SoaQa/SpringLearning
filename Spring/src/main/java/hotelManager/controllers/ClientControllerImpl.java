package hotelManager.controllers;

import hotelManager.controllers.interfaces.IClientController;
import hotelManager.models.HotelClient;
import hotelManager.services.interfaces.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tools.builder.annotations.ControlledObject;
import tools.builder.annotations.InjectDependency;
import utils.ReflectionConstructor;
import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
public class ClientControllerImpl implements IClientController {
    /**
     * @return hotelClient
     */
    @Autowired
    private IClientService clientService;



    public HotelClient createHotelClient(String name, String surname, int age, long roomID, int cDays) {
        return clientService.createHotelClient(name, surname, age, roomID, cDays);
    }

    public List<HotelClient> getSortedClients(Comparator comparator){
        return clientService.getSortedClients(comparator);
    }

    public List<HotelClient> getHotelClients(){
        return clientService.getHotelClients();
    }

}
