package tools.builder;


import lombok.extern.slf4j.Slf4j;
import tools.builder.annotations.ConfigProperty;
import tools.builder.annotations.ControlledObject;
import tools.builder.interfaces.IBuilder;
import tools.config.ConfigReader;
import tools.config.interfaces.IConfigReader;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
public class Builder implements IBuilder {
    private IConfigReader configReader = new ConfigReader();
    private String configPath = "src/main/resources/";
    private String configName = "application.yml";
    public Map config = configReader.readConfig(configPath, configName);

    public Builder() throws IOException {
    }


    public void configure(Object o){
        /**
         * Перадаем класс, если класс имеет нужную аннотацию, то пробегаем по его полям и кофигурируем
         * конфиг кэшируется в мапу.
         */
        Class c = o.getClass();
        if(!c.isAnnotationPresent(ControlledObject.class)){

            log.warn("No annotation ControlledObject!" + c.getName());

        }
        else {
            Field[] fields = c.getDeclaredFields();

            for (Field f: fields)
            {
                if(f.isAnnotationPresent(ConfigProperty.class)){
                    ConfigProperty annotation = f.getDeclaredAnnotation(ConfigProperty.class);

                    try{
                        if (!config.containsKey(annotation.propertyName())){

                            log.warn(String.format("В конфиге отсутвует требуемый параметр : %s!", annotation.propertyName()));
                            System.out.println(String.format("В конфиге отсутвует требуемый параметр : %s!", annotation.propertyName()));

                        }
                        else{

                            f.setAccessible(true);
                            f.set(o, config.get(annotation.propertyName()));

                        }
                    }
                    catch (IllegalAccessException e){

                        log.error(e.getMessage(), e);
                        System.out.println(e.getMessage());

                    }
                }
            }

        }

    }

}
