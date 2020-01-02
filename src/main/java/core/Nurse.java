package core;

import annotations.Inject;
import containers.Container;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Nurse {

    private Container container;

    public Nurse(Container container) {
        this.container = container;
    }

    //TODO: needed call this after call the default constructor
    public void dependenciesResolve(Object obj){
        Field[] fields = obj.getClass().getFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> inject(obj, field));
    }

    private void inject(Object obj, Field field) {
        String injectionName = field.getType().getName();
        Object injection = container.getDependency(injectionName);
        field.setAccessible(true);
        try {
            field.set(obj, injection);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
