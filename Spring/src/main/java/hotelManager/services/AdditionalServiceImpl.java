package hotelManager.services;

import hotelManager.dao.interfaces.IAdditionalServiceDao;
import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.interfaces.IAdditionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.builder.annotations.ControlledObject;
import tools.builder.annotations.InjectDependency;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdditionalServiceImpl implements IAdditionalService {

    @Autowired
    private IAdditionalServiceDao additionalServiceDao;



    public hotelManager.models.AdditionalService createAService(String name, int cost, Room room, HotelClient client, int cDays){
        hotelManager.models.AdditionalService additionalService = new hotelManager.models.AdditionalService();
        additionalService.setServiceCost(cost);
        additionalService.setServiceName(name);
        additionalService.setRoom(room);
        Date now = new Date();
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate rentDate = localDate.plusDays(cDays);
        additionalService.setRentDate(rentDate);
        additionalServiceDao.merge(additionalService);
        return additionalService;
    }

    public void addAdditionalService(AdditionalService additionalService) {
        additionalServiceDao.merge(additionalService);

    }

    public List<hotelManager.models.AdditionalService> getAdditionalServices(){
        return additionalServiceDao.getAllData();
    }


}
