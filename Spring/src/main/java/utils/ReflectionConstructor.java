package utils;


import lombok.extern.slf4j.Slf4j;
import tools.builder.Builder;
import tools.builder.interfaces.IBuilder;
import tools.di.InjectorImpl;
import tools.di.interfaces.Injector;
import java.io.IOException;

@Slf4j
public class ReflectionConstructor {
    private static ReflectionConstructor INSTANCE;


    public static synchronized ReflectionConstructor getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ReflectionConstructor();
        }
        return INSTANCE;
    }

    public void buildThis(Object o) {
        try{
            IBuilder builder = new Builder();
            Injector injectorImpl = new InjectorImpl();
            injectorImpl.inject(o);
            builder.configure(o);

        }
        catch (IllegalAccessException | IOException e){
            log.error(e.getMessage(), e);
            System.out.println(e.getMessage());
        }

        catch (Exception e){
            log.error(e.getMessage(), e);
            System.out.println(e.getMessage());
        }
    }
}
