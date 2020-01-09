package tools.builder.interfaces;

import java.io.IOException;

public interface IBuilder {
    void configure(Object o) throws IllegalAccessException, IOException;
}
