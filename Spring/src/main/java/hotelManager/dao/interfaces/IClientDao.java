package hotelManager.dao.interfaces;

import hotelManager.models.ArchiveClient;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;

import java.util.List;

public interface IClientDao extends IGenericDao<HotelClient, Long> {
    List<ArchiveClient> getHistorycalClients(Room room, int cnt);
}
