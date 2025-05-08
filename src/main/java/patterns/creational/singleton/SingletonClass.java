package patterns.creational.singleton;

public enum SingletonClass {
    INSTANCE;

    SingletonClass() {
        System.out.println("Instance Created");
    }
}