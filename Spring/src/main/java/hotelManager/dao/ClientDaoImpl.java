package hotelManager.dao;

import hotelManager.dao.interfaces.IClientDao;
import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class ClientDaoImpl extends GenericDaoImpl<HotelClient, Long> implements IClientDao {

    public List<ArchiveClient> getHistorycalClients(Room room, int cnt){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ArchiveClient> criteria = builder.createQuery(ArchiveClient.class);
        Root<ArchiveClient> clientRoot = criteria.from(ArchiveClient.class);
        return session.createQuery(criteria.select(clientRoot)
                .where(builder.equal(clientRoot.get("room"), room.getId()))
                .orderBy(builder.desc(clientRoot.get("evictDate")))).setMaxResults(cnt).getResultList();
    };
}
