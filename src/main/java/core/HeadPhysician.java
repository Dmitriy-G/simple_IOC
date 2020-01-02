package core;

import com.google.common.reflect.ClassPath;
import containers.Container;
import rules.ClinicRulesConfiguration;

import java.io.IOException;
import java.util.Arrays;

// Configurator
public class HeadPhysician {

    private Container container;

    public HeadPhysician(Container container) {
        this.container = container;
    }

    public void resolveClinicRules(){
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        //TODO: need to use some class path for scan
        //TODO: what if config classes will be more than one?
        try {
            ClassPath.from(loader).getTopLevelClasses()
                    .stream()
                    .map(ClassPath.ClassInfo::load)
                    .filter(clazz -> Arrays.asList(clazz.getInterfaces()).contains(ClinicRulesConfiguration.class))
                    .forEach(clazz -> {
                        try {
                           container.addDependency(clazz.getName(), clazz.newInstance());
                        } catch (IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
