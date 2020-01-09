package tools.config.interfaces;

import java.io.IOException;
import java.util.Map;

public interface IConfigReader {
    Map readConfig(String configPath, String cfgFileName) throws IOException;
}
