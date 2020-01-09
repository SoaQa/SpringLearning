package hotelManager.java_config.post_processors;

import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.Room;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * не используется.
 */
public class RoomDaoBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("Called postProcessBeforeInitialization() for :" + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        IRoomDao roomDao = (IRoomDao) bean;
        roomDao.setGenericClass(Room.class);
        return bean;
    }
}
