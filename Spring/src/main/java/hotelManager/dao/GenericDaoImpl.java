package hotelManager.dao;

import hotelManager.dao.interfaces.IGenericDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Slf4j
public abstract class GenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {
    protected Class<T> cl;
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void setGenericClass( Class< T > c ) {
        this.cl = c;
    }

    public void setAllData(List<T> l) {
        Session session = sessionFactory.openSession();
        try{
            for (T o: l
            ) {
                session.save(o);
            }
            session.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

    public List<T> getAllData() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.cl);
        criteria.from(this.cl);
        return session.createQuery(criteria).getResultList();
    }

    public T getByID(PK id) {
        Session session = sessionFactory.openSession();
        return session.get(this.cl, id);
    }

    public void removeByID(PK id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            T ra = session.get(this.cl, id);
            session.remove(ra);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

    public void merge(T o){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.merge(o);
            session.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

}
