package patterns.creational.singleton;

import java.io.Serializable;
import java.util.UUID;

//LAZY Instantiation
public final class SingletonThreadSafety implements Serializable {

    public static volatile SingletonThreadSafety instance;

    public final String counter;

    private SingletonThreadSafety(String counter) {
        this.counter = counter;
        if (instance != null) {
            throw new IllegalStateException("Instance already created");
        }
    }

    public static SingletonThreadSafety getInstance(String value) {
        SingletonThreadSafety result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SingletonThreadSafety.class) {
            if (instance == null) {
                instance = new SingletonThreadSafety(value);
            }
            return instance;
        }
    }

    public void showMessage() {
        System.out.println("Hello, I am a SingletonThreadSafety!");
    }

    public class Main {
        public static void main(String[] args) {

            SingletonThreadSafety singleton = SingletonThreadSafety.getInstance(UUID.randomUUID().toString());

            singleton.showMessage();
        }
    }

}
