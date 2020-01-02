package core;

import annotations.Cure;
import com.google.common.reflect.ClassPath;
import containers.Container;

import java.io.IOException;

public class Registrar {
    private Container container;

    public Registrar(Container container) {
        this.container = container;
    }

    public Registrar register(String pathToPackage){
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            ClassPath.from(loader).getTopLevelClasses()
                    .stream()
                    .filter(info -> info.getName().startsWith(pathToPackage))
                    .map(ClassPath.ClassInfo::load)
                    .filter(clazz -> clazz.isAnnotationPresent(Cure.class))
                    .forEach(clazz -> {
                        try {
                            container.addDependency(clazz.getName(), clazz.newInstance());
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

}
