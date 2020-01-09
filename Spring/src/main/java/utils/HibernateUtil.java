package utils;

import hotelManager.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(Room.class);
        cfg.addAnnotatedClass(Hotel.class);
        cfg.addAnnotatedClass(AdditionalService.class);
        cfg.addAnnotatedClass(HotelClient.class);
        cfg.addAnnotatedClass(ArchiveClient.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        sessionFactory = cfg.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
