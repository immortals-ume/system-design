package design.patterns.creational.singleton;

public final class SingletonEagerly {

    private static final SingletonEagerly instance = new SingletonEagerly();

    private SingletonEagerly() {
    }

    public static SingletonEagerly getInstance() {
        return instance;
    }
}
