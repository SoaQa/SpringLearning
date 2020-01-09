package hotelManager.dao;

import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.Room;
import org.hibernate.Session;
import utils.RoomStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class RoomDaoImpl extends GenericDaoImpl<Room, Long> implements IRoomDao {

    @Override
    public List<Room> getFreeOrBusyRooms(RoomStatus p) {
        boolean param;

        if (p == RoomStatus.FREE){
            param = true;
        }
        else if(p == RoomStatus.BUSY){
            param = false;
        }
        else {
            throw new RuntimeException("Wrong rooms status for query!");
        };

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteria = builder.createQuery(this.cl);
        Root<Room> roomRoot = criteria.from(Room.class);
        return session.createQuery(criteria.select(roomRoot)
                .where(builder.equal(roomRoot.get("isFree"), param))).getResultList();
    }

    @Override
    public List<Room> getRoomsWillBeFreeAfter(int cDays) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Room> criteria = builder.createQuery(this.cl);
        Root<Room> roomRoot = criteria.from(Room.class);
        System.out.println(LocalDate.now().plusDays(cDays));
        return session.createQuery(criteria.select(roomRoot)
                .where(builder.greaterThan(roomRoot.get("rentDate"), LocalDate.now().plusDays(cDays)))).getResultList();
    }

}
