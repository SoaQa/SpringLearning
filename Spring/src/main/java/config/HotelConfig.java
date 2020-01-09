package config;

import hotelManager.dao.AdditionalServiceDaoImpl;
import hotelManager.dao.ClientDaoImpl;
import hotelManager.dao.RoomDaoImpl;
import hotelManager.dao.interfaces.IAdditionalServiceDao;
import hotelManager.dao.interfaces.IClientDao;
import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScans({
    @ComponentScan(basePackages = "hotelManager"),
    @ComponentScan(basePackages = "ui")
})
public class HotelConfig {
    @Bean
    public IRoomDao roomDao(){
        IRoomDao roomDao = new RoomDaoImpl();
        roomDao.setGenericClass(Room.class);
        return roomDao;
    }

    @Bean
   public IClientDao clientDao(){
       IClientDao clientDao = new ClientDaoImpl();
       clientDao.setGenericClass(HotelClient.class);
       return clientDao;
   }

    @Bean
    public IAdditionalServiceDao additionalServiceDao(){
        IAdditionalServiceDao additionalServiceDao = new AdditionalServiceDaoImpl();
        additionalServiceDao.setGenericClass(AdditionalService.class);
        return additionalServiceDao;
    }

}
