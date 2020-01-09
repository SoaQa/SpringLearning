package hotelManager.controllers;

import hotelManager.controllers.interfaces.IHotelController;
import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.Hotel;
import hotelManager.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import utils.ReflectionConstructor;

import java.util.Random;

@Controller
public class HotelControllerImpl implements IHotelController {
    @Autowired
    private IRoomDao roomDao;


    public void createHotel() {

        Hotel hotel = new Hotel();
        hotel.setHotelName("Panorama");

        for (int i = 1; i <= 10; i++) {
            Room room = new Room();
            room.setNRoom(i);
            room.setCostPerDay((new Random().nextInt(500) + i) + 500);
            room.setStarsCount(new Random().nextInt(5));
            room.setMaximumOccupancy(new Random().nextInt(5) + 1);
            roomDao.merge(room);
        }
    }

}
