package tools.di.interfaces;

public interface Injector {
    void inject(Object o) throws IllegalAccessException;
}
