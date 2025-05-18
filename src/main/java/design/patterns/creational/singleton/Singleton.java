package design.patterns.creational.singleton;

import java.util.UUID;

//LAZY Instantiation
public final class Singleton {

    public static Singleton instance;

    public final String counter;

    private Singleton(String counter) {
        this.counter = counter;
    }

    public static Singleton getInstance(String counter) {
        if (instance == null) {
            instance = new Singleton(counter);
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello, I am a SingletonClass!");
    }

    public class Main {
        public static void main(String[] args) {

            Singleton singleton = Singleton.getInstance(UUID.randomUUID().toString());

            singleton.showMessage();
        }
    }
}
