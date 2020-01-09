package tools.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import tools.config.interfaces.IConfigReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class ConfigReader implements IConfigReader {

    public Map readConfig(String configPath, String cfgFileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(configPath, cfgFileName), Map.class);
    }

}
