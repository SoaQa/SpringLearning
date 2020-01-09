package tools.di;


import hotelManager.dao.AdditionalServiceDaoImpl;
import hotelManager.dao.ClientDaoImpl;
import hotelManager.dao.RoomDaoImpl;
import hotelManager.dao.interfaces.IAdditionalServiceDao;
import hotelManager.dao.interfaces.IClientDao;
import hotelManager.dao.interfaces.IRoomDao;
import hotelManager.models.AdditionalService;
import hotelManager.models.HotelClient;
import hotelManager.models.Room;
import hotelManager.services.AdditionalServiceImpl;
import hotelManager.services.ClientServiceImpl;
import hotelManager.services.RoomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import tools.builder.annotations.ControlledObject;
import tools.builder.annotations.InjectDependency;
import tools.di.interfaces.Injector;
import utils.ReflectionConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InjectorImpl implements Injector {
    private Map<String, Object> dependencies =  new HashMap<>();
    public InjectorImpl() {
        createDependencies();
    }



    public void inject(Object o) throws IllegalAccessException {
        Class c = o.getClass();

        if (!c.isAnnotationPresent(ControlledObject.class)) {

            log.warn("No annotation ControlledObject!" + c.getName());


        } else {
            Field[] fields = c.getDeclaredFields();

            for (Field f : fields) {

                if (f.isAnnotationPresent(InjectDependency.class)) {

                    InjectDependency annotation = f.getDeclaredAnnotation(InjectDependency.class);
                    try{

                        if (dependencies.containsKey(annotation.dependencyName())){
                            f.setAccessible(true);
                            f.set(o, dependencies.get(annotation.dependencyName()));

                        }

                        else{

                            log.warn("Неизвестный параметр!");
                            throw new RuntimeException("Неизвестный параметр! " + annotation.dependencyName());
                        }

                    }
                    catch (IllegalAccessException e){

                        log.error(e.getMessage(), e);

                        System.out.println(e.getMessage());
                    }
                    /**
                     * Проверим что зависимост сама не нуждается в настройке.
                     */
                    ReflectionConstructor.getInstance().buildThis(f.get(o));
                }
            }
        }
    }



    private void createDependencies(){
        dependencies.put("roomService", new RoomServiceImpl());
        dependencies.put("additionalService", new AdditionalServiceImpl());
        dependencies.put("clientService", new ClientServiceImpl());

        IRoomDao roomDao = new RoomDaoImpl();
        roomDao.setGenericClass(Room.class);
        dependencies.put("roomDao", roomDao);

        IAdditionalServiceDao addDao = new AdditionalServiceDaoImpl();
        addDao.setGenericClass(AdditionalService.class);
        dependencies.put("additionalServiceDao", addDao);

        IClientDao clientDao = new ClientDaoImpl();
        clientDao.setGenericClass(HotelClient.class);
        dependencies.put("clientDao", clientDao);
    }
}
