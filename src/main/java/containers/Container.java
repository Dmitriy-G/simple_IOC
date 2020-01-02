package containers;

public interface Container {
    void addDependency(String name, Object dependency);
    Object getDependency(String name);
}
