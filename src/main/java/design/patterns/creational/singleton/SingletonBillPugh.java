package design.patterns.creational.singleton;

public class SingletonBillPugh {
    private SingletonBillPugh() {
    }

    public static SingletonBillPugh getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final SingletonBillPugh instance = new SingletonBillPugh();
    }
}