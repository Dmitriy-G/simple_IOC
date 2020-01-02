package containers;

import java.util.HashMap;
import java.util.Map;

public class SimpleContainerViaMap implements Container {

    private Map<String, Object> storage;

    public SimpleContainerViaMap() {
        this.storage = new HashMap<>();
    }

    @Override
    public void addDependency(String name, Object dependency) {
        storage.put(name, dependency);
    }

    @Override
    public Object getDependency(String name) {
        return storage.get(name);
    }
}
