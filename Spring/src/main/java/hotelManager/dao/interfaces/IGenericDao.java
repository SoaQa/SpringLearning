package hotelManager.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {
    void setGenericClass( Class< T > c );
    List<T> getAllData();
    void setAllData(List<T> l);
    T getByID(PK id);
    void removeByID(PK id);
    void merge(T o);
}
