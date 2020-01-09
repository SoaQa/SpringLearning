package utils;

import config.HotelConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MyAppContext {
    private static AnnotationConfigApplicationContext applicationContext;
    static {
        applicationContext = new AnnotationConfigApplicationContext(HotelConfig.class);
    }

    public static AnnotationConfigApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
