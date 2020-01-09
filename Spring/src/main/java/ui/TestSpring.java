package ui;

import hotelManager.dao.interfaces.IRoomDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class TestSpring {
    @Autowired
    private IRoomDao roomDao;

    public void printer(){
        System.out.println(roomDao.getAllData());
    }
}
